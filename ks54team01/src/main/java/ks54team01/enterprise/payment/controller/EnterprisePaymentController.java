package ks54team01.enterprise.payment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;
import ks54team01.enterprise.payment.service.EnterprisePaymentService;
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
