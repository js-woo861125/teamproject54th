package ks54team01.customer.payment.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.contract.service.CustomerContractService;
import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;
import ks54team01.customer.payment.service.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer/payment")
@RequiredArgsConstructor
@Slf4j
public class CustomerPaymentController {
	
	private final CustomerPaymentService customerPaymentService;
	
	private final CustomerContractService customerContractService;
	
	
	
	@GetMapping("/detail")
	public String getPaymentDetailList(@RequestParam("rentalContractNo") String rentalContractNo, Model model) {
		
		List<CustomerPayment> paymentDetail =  customerPaymentService.getPaymentDetailList(rentalContractNo);
		
		CustomerPayment firstPaymentDetail = customerPaymentService.getFirstPaymentDetail(rentalContractNo);
		
		String prodNm = firstPaymentDetail.getProdNm();
		
		String rentalContactNo = firstPaymentDetail.getRentalContractNo();
		
		String paymentType = firstPaymentDetail.getPaymentType();
		
		Integer paymentCount = firstPaymentDetail.getPaymentCount();
		
		Integer totalPrice = firstPaymentDetail.getTotalPrice();
		
		
		
		model.addAttribute("title", "주문상세내역");
		model.addAttribute("paymentDetail", paymentDetail);
		model.addAttribute("prodNm", prodNm);
		model.addAttribute("rentalContactNo", rentalContactNo);
		model.addAttribute("paymentType", paymentType);
		model.addAttribute("paymentCount", paymentCount);
		model.addAttribute("totalPrice", totalPrice);
		
		
		return "customer/myPage/myPaymentDetailView";
	}
	
	
	
	@PostMapping("/refund")
	public ResponseEntity<String> addRefund(@RequestParam("orderId") String orderId, @RequestParam("paymentCompletedNo") String paymentCompletedNo
										  , @RequestParam("paymentKey") String paymentKey, @RequestParam String refundReason
										  , @RequestParam("entCeoNo") String entCeoNo, @RequestParam("entEmpId") String entEmpId, HttpSession session) {
			
		String custId = (String) session.getAttribute("loginId");
		
		
		
		customerPaymentService.addRefund(orderId, paymentCompletedNo, paymentKey, refundReason, custId, entCeoNo, entEmpId);
		
		customerPaymentService.modifyPaymentStatus(orderId, "환불요청");
		
		return ResponseEntity.ok("환불 요청이 접수되었습니다.");
	}
	
	
	
	
	@GetMapping("/modifyBilling/success")
	public String modifyBillingPayment(@RequestParam("authKey") String authKey
									 , @RequestParam("customerKey") String customerKey, @RequestParam("rentalContractNo") String rentalContractNo, HttpSession session) {
		
		String custId = (String) session.getAttribute("loginId");
		
		Map<String, Object> responseMap = customerPaymentService.getBillingKey(authKey, customerKey);
	    String billingKey = (String) responseMap.get("billingKey");
	    
	    log.info("billingKey: {}", billingKey);
	    
	    customerPaymentService.modifyBillingKey(custId, billingKey, rentalContractNo);
		
		
		
		return "redirect:/customer/payment/paymentList";
	}
	
	
	
	

	@GetMapping("/regularPayment")
	public String getRegularPayment(@RequestParam("prodUnitPrice") int prodUnitPrice, @RequestParam("totalPrice") int totalPrice,
								    @RequestParam("managerId") String managerId, @RequestParam("period") int period,
								    @RequestParam("entCeoNo") String entCeoNo,  @RequestParam("entEmpId") String entEmpId,
								    @RequestParam("orderQuantity") int orderQuantity, @RequestParam("productsName") String productsName,
								    @RequestParam("sellProductsNo") String sellProductsNo, @RequestParam("productsNum") String productsNum, HttpSession session, Model model) {
		
				
		String custId = (String) session.getAttribute("loginId");
				
		model.addAttribute("prodUnitPrice", prodUnitPrice);
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("managerId", managerId);
	    model.addAttribute("period", period);
	    model.addAttribute("entCeoNo", entCeoNo);
	    model.addAttribute("entEmpId", entEmpId);
	    model.addAttribute("productsName", productsName);
	    model.addAttribute("orderQuantity", orderQuantity);
	    model.addAttribute("sellProductsNo", sellProductsNo);
	    model.addAttribute("productsNum", productsNum);
	    model.addAttribute("custId", custId);
		
		
        List<CustomerDelivery> deliveryList = customerPaymentService.getDeliveryListById(custId);
        
        int quantity = customerPaymentService.getQuantity(productsNum, entCeoNo);
        
        model.addAttribute("deliveryList", deliveryList);
        model.addAttribute("quantity", quantity);
        
        log.info("quantity: {}", quantity);
		
		
		
		return "customer/payment/regularPaymentView";
	}
	
	
	@PostMapping("/cancel")
	@ResponseBody
	public ResponseEntity<?> cancelPayment(@RequestParam("orderId") String orderId
										 , @RequestParam("paymentCompletedNo") String paymentCompletedNo) {
		try {
			
			String paymentKey = customerPaymentService.getPaymentKeyByOrderId(orderId);
			
			if(paymentKey == null) {
				return ResponseEntity.badRequest().body("결제 정보가 존재하지 않습니다.");
			}
			
			
			customerPaymentService.cancelPayment(paymentKey, "고객 요청 취소");
			
			customerPaymentService.modifyPaymentStatus(orderId, "주문취소");
			
			int cancelQuantity = customerPaymentService.getQuantityByOrderId(orderId);
			
			CustomerPayment cancelProduct = customerPaymentService.getProductByOrderId(orderId);
			
			customerPaymentService.modifyCancelQuantity(cancelProduct, cancelQuantity);
			
			customerPaymentService.removeDeliveryInfo(paymentCompletedNo);
			
			String rentalContractNo = customerPaymentService.getRentalContractNo(paymentCompletedNo);
			
			customerContractService.modifyRentalContractStatus(rentalContractNo);
			
			return ResponseEntity.ok("주문이 취소되었습니다.");
			
		} catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	
	
	@GetMapping("/paymentList")
	public String getPaymentList(HttpSession session, Model model) {
		
		String custId = (String) session.getAttribute("loginId");
		
		
		
		List<CustomerPayment> paymentList = customerPaymentService.getPaymentList(custId);
		
		
		model.addAttribute("title", "주문 목록");
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("custId", custId);
		
		
		return "customer/myPage/myPaymentListView";
	}
	
	
	
	
	@GetMapping("/addPayment")
	public String addPayment(CustomerPayment customerPayment, @RequestParam("delNo") String delNo
							, @RequestParam("quantity") Integer quantity, @RequestParam("prodNo") String prodNo
							, @RequestParam("delRecipientNm") String delRecipientNm, @RequestParam("delRecipientPhone") String delRecipientPhone
							, @RequestParam("delRequest") String delRequest, Model model) {
		
		int orderQuantity = customerPayment.getPaymentCount();
		String entCeoNo = customerPayment.getEntCeoNo();
		
		customerPaymentService.modifyQuantity(orderQuantity, prodNo, entCeoNo);
		
		
		customerPaymentService.addPayment(customerPayment);
		
		String paymentCompletedNo = customerPayment.getPaymentCompletedNo();
		String empId = customerPayment.getEntEmpId();
		String ceoNo = customerPayment.getEntCeoNo();
		String custId = customerPayment.getCustId();
		String sellProdNo = customerPayment.getSellProdNo();
		
		CustomerDeliveryInfo customerDeliveryInfo = new CustomerDeliveryInfo();
		
		String delInfoNo = "del_info_" + UUID.randomUUID().toString().replace("-", "");
		customerDeliveryInfo.setDelInfoNo(delInfoNo);
		
		customerDeliveryInfo.setDelNo(delNo);
		customerDeliveryInfo.setPaymentCompletedNo(paymentCompletedNo);
		customerDeliveryInfo.setEntCeoNo(ceoNo);
		customerDeliveryInfo.setEntEmpId(empId);
		customerDeliveryInfo.setCustId(custId);
		customerDeliveryInfo.setSellProdNo(sellProdNo);
		customerDeliveryInfo.setRecipientNm(delRecipientNm);
		customerDeliveryInfo.setRecipientPhone(delRecipientPhone);
		customerDeliveryInfo.setDelRequest(delRequest);
		
		customerPaymentService.addDeliveryInfo(customerDeliveryInfo);
		
		return "redirect:/customer/payment/paymentList";
	}
	
	

	@GetMapping("/addBillingPayment")
	public String addBillingPaymnet(CustomerPayment customerPayment, @RequestParam("delNo") String delNo
								  , @RequestParam("quantity") Integer quantity, @RequestParam("prodNo") String prodNo
								  , @RequestParam("delRecipientNm") String delRecipientNm, @RequestParam("delRecipientPhone") String delRecipientPhone
								  , @RequestParam("delRequest") String delRequest, @RequestParam("rentalContractNo") String rentalContractNo, Model model) {
		
		int orderQuantity = customerPayment.getPaymentCount();
		String entCeoNo = customerPayment.getEntCeoNo();
		
		customerPaymentService.modifyQuantity(orderQuantity, prodNo, entCeoNo);
		
		customerPaymentService.addBillingPayment(customerPayment, rentalContractNo);
		
		
		String paymentCompletedNo = customerPayment.getPaymentCompletedNo();
		String empId = customerPayment.getEntEmpId();
		String ceoNo = customerPayment.getEntCeoNo();
		String custId = customerPayment.getCustId();
		String sellProdNo = customerPayment.getSellProdNo();
		
		CustomerDeliveryInfo customerDeliveryInfo = new CustomerDeliveryInfo();
		
		String delInfoNo = "del_info_" + UUID.randomUUID().toString().replace("-", "");
		customerDeliveryInfo.setDelInfoNo(delInfoNo);
		
		customerDeliveryInfo.setDelNo(delNo);
		customerDeliveryInfo.setPaymentCompletedNo(paymentCompletedNo);
		customerDeliveryInfo.setEntCeoNo(ceoNo);
		customerDeliveryInfo.setEntEmpId(empId);
		customerDeliveryInfo.setCustId(custId);
		customerDeliveryInfo.setSellProdNo(sellProdNo);
		customerDeliveryInfo.setRecipientNm(delRecipientNm);
		customerDeliveryInfo.setRecipientPhone(delRecipientPhone);
		customerDeliveryInfo.setDelRequest(delRequest);
		
		customerPaymentService.addDeliveryInfo(customerDeliveryInfo);
		
		return "redirect:/customer/payment/paymentList";
	}
	
	
	
	
	@GetMapping("/order")
	public String getOrder( @RequestParam("prodUnitPrice") int prodUnitPrice, @RequestParam("totalPrice") int totalPrice,
						    @RequestParam("managerId") String managerId, @RequestParam("period") int period,
						    @RequestParam("entCeoNo") String entCeoNo,  @RequestParam("entEmpId") String entEmpId,
						    @RequestParam("orderQuantity") int orderQuantity, @RequestParam("productsName") String productsName,
						    @RequestParam("sellProductsNo") String sellProductsNo, @RequestParam("productsNum") String productsNum, HttpSession session, Model model) {
		
		String custId = (String) session.getAttribute("loginId");
		
		model.addAttribute("prodUnitPrice", prodUnitPrice);
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("managerId", managerId);
	    model.addAttribute("period", period);
	    model.addAttribute("entCeoNo", entCeoNo);
	    model.addAttribute("entEmpId", entEmpId);
	    model.addAttribute("productsName", productsName);
	    model.addAttribute("orderQuantity", orderQuantity);
	    model.addAttribute("sellProductsNo", sellProductsNo);
	    model.addAttribute("productsNum", productsNum);
	    model.addAttribute("custId", custId);
		
		
        List<CustomerDelivery> deliveryList = customerPaymentService.getDeliveryListById(custId);
        
        int quantity = customerPaymentService.getQuantity(productsNum, entCeoNo);
        
        model.addAttribute("deliveryList", deliveryList);
        model.addAttribute("quantity", quantity);
        
        log.info("quantity: {}", quantity);
		
		return "customer/payment/order";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/success")
	public String paymentSuccess( @RequestParam("paymentKey") String paymentKey, @RequestParam("orderId") String orderId
					            , @RequestParam("amount") Long amount, @RequestParam("sellProductsNo") String sellProductsNo
					            , @RequestParam("prodUnitPrice") Integer prodUnitPrice, @RequestParam("orderQuantity") Integer orderQuantity
					            , @RequestParam("entCeoNo") String entCeoNo, @RequestParam("entEmpId") String entEmpId
					            , @RequestParam("managerId") String managerId, @RequestParam("delNo") String delNo
					            , @RequestParam("quantity") Integer quantity, @RequestParam("prodNo") String prodNo
					            , @RequestParam("delRecipientNm") String delRecipientNm, @RequestParam("delRecipientPhone") String delRecipientPhone
					            , @RequestParam("delRequest") String delRequest, Model model, RedirectAttributes reAttr, HttpSession session){

        log.info("paymentKey: {} ", paymentKey);
        log.info("orderId: {} ", orderId);
        log.info("amount: {} ", amount);
        log.info("sellProductsNo: {} ", sellProductsNo);
        log.info("prodUnitPrice: {} ", prodUnitPrice);
        log.info("orderQuantity: {} ", orderQuantity);
        
        String custId = (String) session.getAttribute("loginId");
        
        
        Map<String, Object> responseMap = customerPaymentService.confirmPaymemt(paymentKey, orderId, amount);
        Map<String, Object> easyPay = (Map<String, Object>) responseMap.get("easyPay");
        
        String provider =  (String) easyPay.get("provider");
        log.info("provider: {} ", provider);
        
        int totalAmount = (int) responseMap.get("totalAmount");
        log.info("totalAmount : {}", totalAmount);
        log.info("custId : {}", custId);
        
        
        reAttr.addAttribute("sellProdNo", sellProductsNo);
        reAttr.addAttribute("prodNo", prodNo);
        reAttr.addAttribute("paymentType", provider);
        reAttr.addAttribute("custId", custId);
        reAttr.addAttribute("totalPrice", totalAmount);
        reAttr.addAttribute("paymentCount", orderQuantity);
        reAttr.addAttribute("prodUnitPrice", prodUnitPrice);
        reAttr.addAttribute("entCeoNo", entCeoNo);
        reAttr.addAttribute("entEmpId", entEmpId);
        reAttr.addAttribute("managerId", managerId);
        reAttr.addAttribute("delNo", delNo);
        reAttr.addAttribute("quantity", quantity);
        reAttr.addAttribute("paymentKey", paymentKey);
        reAttr.addAttribute("orderId", orderId);
        reAttr.addAttribute("delRecipientNm", delRecipientNm);
        reAttr.addAttribute("delRecipientPhone", delRecipientPhone);
        reAttr.addAttribute("delRequest", delRequest);
        
        return "redirect:/customer/payment/addPayment";
	}
	
	
	@GetMapping("/billing/success")
	public String billingPaymentSuccess( @RequestParam("sellProductsNo") String sellProductsNo, @RequestParam("prodUnitPrice") Integer prodUnitPrice
						               , @RequestParam("orderQuantity") Integer orderQuantity, @RequestParam("entCeoNo") String entCeoNo
						               , @RequestParam("entEmpId") String entEmpId , @RequestParam("managerId") String managerId, @RequestParam("delNo") String delNo
						               , @RequestParam("quantity") Integer quantity, @RequestParam("prodNo") String prodNo
						               , @RequestParam("delRecipientNm") String delRecipientNm, @RequestParam("delRecipientPhone") String delRecipientPhone
						               , @RequestParam("delRequest") String delRequest, @RequestParam("customerKey") String customerKey
						               , @RequestParam("authKey") String authKey, @RequestParam("rentalContractNo") String rentalContractNo
						               , @RequestParam("totalPrice") String totalPrice, @RequestParam("period") Integer period, Model model, RedirectAttributes reAttr, HttpSession session) {

        
		String custId = (String) session.getAttribute("loginId");
        
        Map<String, Object> responseMap = customerPaymentService.getBillingKey(authKey, customerKey);
        
        String billingKey = (String) responseMap.get("billingKey");
        
        log.info("billingKey: {}", billingKey);
       
        reAttr.addAttribute("sellProdNo", sellProductsNo);
        reAttr.addAttribute("prodNo", prodNo);
        reAttr.addAttribute("custId", custId);
        reAttr.addAttribute("paymentCount", orderQuantity);
        reAttr.addAttribute("prodUnitPrice", prodUnitPrice);
        reAttr.addAttribute("totalPrice", totalPrice);
        reAttr.addAttribute("entCeoNo", entCeoNo);
        reAttr.addAttribute("entEmpId", entEmpId);
        reAttr.addAttribute("managerId", managerId);
        reAttr.addAttribute("delNo", delNo);
        reAttr.addAttribute("quantity", quantity);
        reAttr.addAttribute("delRecipientNm", delRecipientNm);
        reAttr.addAttribute("delRecipientPhone", delRecipientPhone);
        reAttr.addAttribute("delRequest", delRequest);
        reAttr.addAttribute("billingKey", billingKey);
        reAttr.addAttribute("customerKey", customerKey);
        reAttr.addAttribute("rentalContractNo", rentalContractNo);
        reAttr.addAttribute("contractPeriod", period);
        
        
        return "redirect:/customer/payment/addBillingPayment";
	}
	
	
}
