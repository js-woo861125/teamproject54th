package ks54team01.admin.payment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.payment.domain.AdminPayment;
import ks54team01.admin.payment.service.AdminPaymentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/payment")
public class AdminPaymentController {

	private final AdminPaymentService adminPaymentService;
	
	
	@GetMapping("/searchPaymentList")
	public String getSearchPaymentList(String searchKey, String searchValue, Model model) {
		
		List<AdminPayment> PaymentList = adminPaymentService.getSearchPaymentList(searchKey, searchValue);
		
		model.addAttribute("title", "결제내역");
		model.addAttribute("PaymentList", PaymentList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/payment/paymentListView";
	}
	
	
	@GetMapping("/paymentList")
	public String getPaymentList(Model model) {
		
		List <AdminPayment> PaymentList = adminPaymentService.getPaymentList();
		
		model.addAttribute("title", "결제내역");
		model.addAttribute("PaymentList", PaymentList);
		
		return "admin/payment/paymentListView";
	}
	
	@GetMapping("/calculate")
	public String getadminpayment() {
		
		return "admin/payment/calculateView";
	}
}
