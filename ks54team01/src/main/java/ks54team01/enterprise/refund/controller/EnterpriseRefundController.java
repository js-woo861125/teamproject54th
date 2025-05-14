package ks54team01.enterprise.refund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprise/refund")
public class EnterpriseRefundController {

	@GetMapping("/refundList")
	public String getRefundList() {
		
		return "enterprise/request/refundRequestListView";
	}
}
