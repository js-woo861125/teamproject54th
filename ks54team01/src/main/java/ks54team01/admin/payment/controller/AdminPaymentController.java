package ks54team01.admin.payment.controller;

import java.time.YearMonth;
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
	
//	입점업체 검색
	@GetMapping("/searchEnterprise")
	public String getSearchEnterprise(@RequestParam(name="searchKey", required = false, defaultValue = "entCeoNo") String searchKey,
			 						@RequestParam(name="searchValue", required = false)String searchValue,
			 						@RequestParam(name="settlementMonth", required = false) String settlementMonth,
			 						Model model) {
		
		// --- settlementMonth가 null 또는 비어있을 경우 현재 월로 설정 (핵심 수정 사항) ---
        if (settlementMonth == null || settlementMonth.isEmpty()) {
            settlementMonth = YearMonth.now().toString(); // 현재 연월을 "YYYY-MM" 형태로 설정 (예: "2025-05")
        }
        // ----------------------------------------------------------------------

        // 1. 입점업체 목록 조회
        List<AdminEntList> entList = adminPaymentService.getSearchEnt(searchKey, searchValue);

        // 2. 관리비(AdminFee) 목록 조회
        List<AdminFee> adminFeeList = adminPaymentService.getAdminPayFee(searchValue, settlementMonth);

        // 3. 월별 정산금액 목록 조회 (처리된 settlementMonth 사용)
        List<AdminMonthlyFee> adminMonthlyFeeList = adminPaymentService.getAdminPaymentCalc(searchValue, settlementMonth);

        // 모델에 데이터 추가 (뷰로 전달)
        model.addAttribute("entList", entList);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("searchValue", searchValue); // 사용자가 입력했던 검색값 유지
        model.addAttribute("adminFeeList", adminFeeList);
        model.addAttribute("adminMonthlyFeeList", adminMonthlyFeeList);
        model.addAttribute("title", "입점업체목록"); // 만약 뷰에서 'title'을 사용한다면 추가

        // --- 처리된 settlementMonth 값을 뷰로 다시 전달 (사용자가 선택했던 월 유지) ---
        model.addAttribute("settlementMonth", settlementMonth);
        // ----------------------------------------------------------------------

        return "admin/payment/calculateView";
    }
	
	
	// 정산페이지 처음 빈 페이지
	@GetMapping("/calculate")
	public String getAdminPaymentCalc() {
				
		return "admin/payment/calculateView";
	}
	
	
	
}
