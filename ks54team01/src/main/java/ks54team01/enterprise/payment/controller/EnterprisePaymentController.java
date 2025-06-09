package ks54team01.enterprise.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;
import ks54team01.enterprise.payment.service.EnterprisePaymentService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise/payment")
@RequiredArgsConstructor
@Slf4j
public class EnterprisePaymentController {

	private final EnterprisePaymentService enterprisePaymentService;
	
	private String mapUnpaidStatusCode(String code) {
		return switch (code) {
		case "1" -> "1.납입예정";
		case "2" -> "2.완납";
		case "3" -> "3.미납";
		case "4" -> "4.납입불필요";
		default -> throw new IllegalArgumentException("Unexpected value: " + code);
		};
	}
	
	
	@GetMapping("/paymentListDetail")
	public String getPaymentDetail(@RequestParam String rentalContractNo, @RequestParam(required = false) String unpaidStatusCode, Model model) {

	    List<EnterprisePaymentDetail> detailList;

	    if (unpaidStatusCode == null || unpaidStatusCode.isEmpty()) {
	        detailList = enterprisePaymentService.getPaymentDetailListByContractNo(rentalContractNo);
	    } else {
	        String unpaidStatus = mapUnpaidStatusCode(unpaidStatusCode);
	        detailList = enterprisePaymentService.getPaymentDetailListByContractNoAndStatus(rentalContractNo, unpaidStatus);
	    }

	    model.addAttribute("PaymentDetailList", detailList);
	    model.addAttribute("rentalContractNo", rentalContractNo);
	    model.addAttribute("selectedCode", unpaidStatusCode);

	    return "enterprise/payment/paymentListDetailView";
	}

	
	
	
	
	
	
	
	@GetMapping("/paymentList")
	public String getPaymentList(@RequestParam(required = false) String searchKey,
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
		
	    PageInfo<EnterprisePayment> paymentList = enterprisePaymentService.getPaymentList(searchParamMap);
		
	    model.addAttribute("title", "주문 목록");
	    model.addAttribute("paymentList", paymentList.getContents());
	    model.addAttribute("currentPage", paymentList.getCurrentPage());
	    model.addAttribute("lastPage", paymentList.getLastPage());
	    model.addAttribute("startPageNum", paymentList.getStartPageNum());
	    model.addAttribute("endPageNum", paymentList.getEndPageNum());
	    model.addAttribute("rowPerPage", pageable.getRowPerPage());
	    model.addAttribute("contentRowCount", paymentList.getTotalRowCount());
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);
		
		
		return "enterprise/payment/paymentListView";
	}
	
}
