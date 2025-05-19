package ks54team01.customer.payment.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.customer.payment.service.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer/payment")
@RequiredArgsConstructor
@Slf4j
public class CustomerPaymentController {
	
	private final CustomerPaymentService customerPaymentService;

	@GetMapping("/addPayment")
	public String getAddPayment() {
		
		return "customer/payment/paymentView";
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/success")
	public String paymentSuccess( @RequestParam("paymentKey") String paymentKey
					            , @RequestParam("orderId") String orderId
					            , @RequestParam("amount") Long amount
					            , @RequestParam("sellProductsNo") String sellProductsNo
					            , @RequestParam("unitPrice") String unitPrice
					            , @RequestParam("quantity") String quantity
					            , Model model
					            , RedirectAttributes reAttr){

        log.info("paymentKey: {} ", paymentKey);
        log.info("orderId: {} ", orderId);
        log.info("amount: {} ", amount);
        log.info("sellProductsNo: {} ", sellProductsNo);
        log.info("unitPrice: {} ", unitPrice);
        log.info("quantity: {} ", quantity);
        
        
        
        Map<String, Object> responseMap = customerPaymentService.confirmPaymemt(paymentKey, orderId, amount);
        Map<String, Object> easyPay = (Map<String, Object>) responseMap.get("easyPay");
        
        String provider =  (String) easyPay.get("provider");
        log.info("provider: {} ", provider);
        
        int totalAmount = (int) responseMap.get("totalAmount");
        log.info("totalAmount : {}", totalAmount);
        
        
        reAttr.addAttribute("sellProductsNo", sellProductsNo);
        reAttr.addAttribute("provider", provider);
        
        return "redirect:/customer/product/productDetail";
	}
}
