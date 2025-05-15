package ks54team01.customer.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer/payment")
@RequiredArgsConstructor
public class CustomerPaymentController {

	@GetMapping("/addPayment")
	public String getAddPayment() {
		
		return "customer/payment/paymentView";
	}
}
