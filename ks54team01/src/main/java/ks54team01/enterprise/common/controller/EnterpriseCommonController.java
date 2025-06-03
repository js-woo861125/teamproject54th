package ks54team01.enterprise.common.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.enterprise.common.service.CommonService;
import ks54team01.enterprise.paymentCalc.controller.PaymentCalcController;
import ks54team01.enterprise.paymentCalc.service.PaymentServiceCalc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise")
@RequiredArgsConstructor
@Slf4j
public class EnterpriseCommonController {

	private final CommonService commonService;
	
	@GetMapping({"","/"})
	public String enterpriseHome(HttpSession session,
								@RequestParam(name="settlementMonth", required = false) String settlementMonth,
								Model model) {
		
		// 메인페이지 금액 표시
		// 현재 연월 설정
		 if (settlementMonth == null || settlementMonth.isEmpty()) {
	            settlementMonth = YearMonth.now().toString(); // 현재 연월을 "YYYY-MM" 형태로 설정 (예: "2025-05")
	        }
		
		 String entCeoNo = (String) session.getAttribute("entCeoNo");
		 
		 List<AdminMonthlyFee> entMonthlyCalcList = commonService.getEntMonthlyCalc(entCeoNo, settlementMonth);
		
		
		
		return "enterprise/main";
	}
}
