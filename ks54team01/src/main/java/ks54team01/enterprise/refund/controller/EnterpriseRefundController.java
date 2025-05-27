package ks54team01.enterprise.refund.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.enterprise.refund.domain.EnterpriseRefund;
import ks54team01.enterprise.refund.service.EnterpriseRefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise/refund")
@RequiredArgsConstructor
@Slf4j
public class EnterpriseRefundController {

	private final EnterpriseRefundService enterpriseRefundService;
	
	
	@PostMapping("/approve")
	@ResponseBody
	public Map<String, String> approveRefund(@RequestParam("orderId") String orderId, @RequestParam("paymentKey") String paymentKey) {
		Map<String, String> result = new HashMap<>();
		try {
			enterpriseRefundService.processRefund(orderId, paymentKey);
			result.put("message", "환불이 성공적으로 처리되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "환불 처리 중 오류 발생: " + e.getMessage());
		}
		return result;
	}
	
	
	
	@GetMapping("/refundList")
	public String getRefundList(HttpSession session ,Model model) {
		
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		
		log.info("entCeoNo:{}", entCeoNo);
		model.addAttribute("title", "환불요청");
		
		List<EnterpriseRefund> refundList = enterpriseRefundService.getRefundList(entCeoNo);
		
		model.addAttribute("refundList", refundList);
		
		log.info("refundList:{}", refundList);
		
		return "enterprise/request/refundRequestListView";
	}
}
