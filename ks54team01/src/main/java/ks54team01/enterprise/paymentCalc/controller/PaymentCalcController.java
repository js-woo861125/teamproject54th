package ks54team01.enterprise.paymentCalc.controller;



import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.enterprise.paymentCalc.service.PaymentServiceCalc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise/paymentClac")
@RequiredArgsConstructor
@Slf4j
public class PaymentCalcController {

	private final PaymentServiceCalc paymentServiceCalc;
	
	@GetMapping("/paymentCalcList")
	public String getPaymentList(HttpSession session,
								@RequestParam(name="settlementMonth", required = false) String settlementMonth,
								Model model) {
		
		// --- settlementMonth가 null 또는 비어있을 경우 현재 월로 설정
        if (settlementMonth == null || settlementMonth.isEmpty()) {
            settlementMonth = YearMonth.now().toString(); // 현재 연월을 "YYYY-MM" 형태로 설정 (예: "2025-05")
        }
		
		String entCeoNo = (String) session.getAttribute("entCeoNo");
	
		List<AdminFee> entPayCalcList = paymentServiceCalc.getEnterPricePayCalc(entCeoNo, settlementMonth);
		
		List<AdminMonthlyFee> adminMonthlyFeeList = paymentServiceCalc.getEntMonthlyCalc(entCeoNo, settlementMonth);
		
		
		log.info("요청된 entCeoNo: {}", entCeoNo);
		log.info("요청된 settlementMonth: {}", settlementMonth);
		log.info("서비스에서 반환된 entPayCalcList (크기): {}", entPayCalcList != null ? entPayCalcList.size() : "null");
		if (entPayCalcList != null) {
			for (int i = 0; i < entPayCalcList.size(); i++) {
				log.info("entPayCalcList[{}] 내용: {}", i, entPayCalcList.get(i));
			}
		}
		
		
		model.addAttribute("entPayCalcList", entPayCalcList);
		
		model.addAttribute("settlementMonth", settlementMonth);
		
		model.addAttribute("adminMonthlyFeeList", adminMonthlyFeeList);
		
		
		
		
		
		return "enterprise/paymentCalc/calculateView";
	}
	
}
