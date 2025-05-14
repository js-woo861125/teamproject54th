package ks54team01.enterprise.payment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.service.EnterprisePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise/payment")
@RequiredArgsConstructor
@Slf4j
public class EnterprisePaymentController {

	private final EnterprisePaymentService enterprisePaymentService;
	
	
	
	@GetMapping("/paymentListDetail")
	public String getPaymentListDetail(Model model) {
		
		return "enterprise/payment/paymentListDetailView";
	}
	
	
	@GetMapping("/searchPaymentList")
	public String getSearchPaymentList(String searchKey, String searchValue, Model model) {
		
		List<EnterprisePayment> PaymentList = enterprisePaymentService.getSearchPaymentList(searchKey, searchValue);
		
		model.addAttribute("title", "주문 목록");
		model.addAttribute("PaymentList", PaymentList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "enterprise/payment/paymentListView";
		
	}
	
	
	@GetMapping("/paymentList")
	public String getPaymentList(Model model) {
		
		List<EnterprisePayment> PaymentList = enterprisePaymentService.getPaymentList();
		
		model.addAttribute("title", "주문 목록");
		model.addAttribute("PaymentList", PaymentList);
		
		
		return "enterprise/payment/paymentListView";
	}
	
}
