package ks54team01.enterprise.paymentCancel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/enterprise/paymentCancel")
@RequiredArgsConstructor
public class EnterprisePaymentCancelController {

	@GetMapping("/paymentCancelList")
	public String getPaymentCancelList() {
		
		return "enterprise/request/paymentCancelRequestListView";
	}
}
