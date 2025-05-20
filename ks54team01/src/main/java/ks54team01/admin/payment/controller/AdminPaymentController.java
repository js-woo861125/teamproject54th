package ks54team01.admin.payment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
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
	
//	 입점업체 검색
	@GetMapping("/searchEnterprise")
	public String getSearchEnterprise(@RequestParam(name="searchKey", required = false, defaultValue = "entCeoNo") String searchKey,
			 						@RequestParam(name="searchValue", required = false)String searchValue,
			 						Model model) {
		
		List<AdminEntList> entList = adminPaymentService.getSearchEnt(searchKey, searchValue);
		
		List<AdminFee> adminFeeList = adminPaymentService.getAdminPayFee(searchValue);
		
		List<AdminMonthlyFee> adminMonthlyFeeList = adminPaymentService.getAdminPaymentCalc(searchValue);
		
		model.addAttribute("entList", entList);
		model.addAttribute("title", "입점업체목록");
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		model.addAttribute("adminFeeList",adminFeeList);
		
		model.addAttribute("adminMonthlyFeeList",adminMonthlyFeeList);
		
		
		return "admin/payment/calculateView";
	}
	
	// 정산페이지 처음 빈 페이지
	@GetMapping("/calculate")
	public String getAdminPaymentCalc() {
				
		return "admin/payment/calculateView";
	}
	
	
	
}
