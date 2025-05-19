package ks54team01.customer.payment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.member.domain.CommonMember;
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

	
	@GetMapping("/paymentList")
	public String getPaymentList(HttpSession session, Model model) {
		
		CommonMember loginMember = (CommonMember) session.getAttribute("loginMember");
		
		if(loginMember == null) {
			return "redirect:/customer/login/memberLogin";
		}
		
		String custId = loginMember.getMemberId();
		
		
		List<CustomerPayment> PaymentList = customerPaymentService.getPaymentList(custId);
		
		model.addAttribute("title", "주문 목록");
		model.addAttribute("PaymentList", PaymentList);
		
		
		return "customer/myPage/myPaymentListView";
	}
	
	
	@GetMapping("/addPayment")
	public String addPayment(CustomerPayment customerPayment) {
		
		customerPaymentService.addPayment(customerPayment);
		
		return "redirect:/customer/payment/paymentList";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/success")
	public String paymentSuccess( @RequestParam("paymentKey") String paymentKey, @RequestParam("orderId") String orderId
					            , @RequestParam("amount") Long amount, @RequestParam("sellProductsNo") String sellProductsNo
					            , @RequestParam("unitPrice") String unitPrice, @RequestParam("quantity") String quantity
					            , Model model, RedirectAttributes reAttr, HttpSession session){

        log.info("paymentKey: {} ", paymentKey);
        log.info("orderId: {} ", orderId);
        log.info("amount: {} ", amount);
        log.info("sellProductsNo: {} ", sellProductsNo);
        log.info("unitPrice: {} ", unitPrice);
        log.info("quantity: {} ", quantity);
        
        CommonMember loginMember = (CommonMember) session.getAttribute("loginMember");
        String custId = loginMember.getMemberId();
        
        
        Map<String, Object> responseMap = customerPaymentService.confirmPaymemt(paymentKey, orderId, amount);
        Map<String, Object> easyPay = (Map<String, Object>) responseMap.get("easyPay");
        
        String provider =  (String) easyPay.get("provider");
        log.info("provider: {} ", provider);
        
        int totalAmount = (int) responseMap.get("totalAmount");
        log.info("totalAmount : {}", totalAmount);
        log.info("custId : {}", custId);
        
        
        reAttr.addAttribute("sellProdNo", sellProductsNo);
        reAttr.addAttribute("paymentType", provider);
        reAttr.addAttribute("custId", custId);
        reAttr.addAttribute("totalPrice", totalAmount);
        reAttr.addAttribute("paymentCount", quantity);
        reAttr.addAttribute("prodUnitPrice", unitPrice);
        
        return "redirect:/customer/payment/addPayment";
	}
}
