package ks54team01.enterprise.refund.controller;

import java.util.HashMap;
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
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise/refund")
@RequiredArgsConstructor
@Slf4j
public class EnterpriseRefundController {

	private final EnterpriseRefundService enterpriseRefundService;
	
	
	
	@PostMapping("/reject")
	@ResponseBody
	public Map<String, Object> rejectRefund(@RequestParam("orderId") String orderId, @RequestParam("paymentKey") String paymentKey) {
		
		enterpriseRefundService.rejectRefund(orderId, paymentKey);
		
		Map<String, Object> result = new HashMap<>();
		result.put("message", "환불 요청이 거부되었습니다.");
		return result;
	}
	
	
	
	@PostMapping("/confirm")
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
	public String getRefundList(@RequestParam(required = false) String searchKey,
							    @RequestParam(required = false) String searchValue,
							    Pageable pageable, Model model, HttpSession session) {
		
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		
		pageable.setRowPerPage(10);
		
		Map<String, Object> searchParamMap = new HashMap<>();
		searchParamMap.put("entCeoNo", entCeoNo);
	    searchParamMap.put("pageable", pageable);

	    if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
	        searchParamMap.put("searchKey", searchKey);
	        searchParamMap.put("searchValue", searchValue);
	    }
		
	    PageInfo<EnterpriseRefund> refundList = enterpriseRefundService.getRefundList(searchParamMap);
		
	    model.addAttribute("title", "배송정보 목록");
	    model.addAttribute("refundList", refundList.getContents());
	    model.addAttribute("currentPage", refundList.getCurrentPage());
	    model.addAttribute("lastPage", refundList.getLastPage());
	    model.addAttribute("startPageNum", refundList.getStartPageNum());
	    model.addAttribute("endPageNum", refundList.getEndPageNum());
	    model.addAttribute("rowPerPage", pageable.getRowPerPage());
	    model.addAttribute("contentRowCount", refundList.getTotalRowCount());
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);
	    
	    
		return "enterprise/request/refundRequestListView";
	}
}
