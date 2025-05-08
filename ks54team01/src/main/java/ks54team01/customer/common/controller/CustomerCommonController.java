package ks54team01.customer.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerCommonController {

	@GetMapping({"","/"})
	public String customerHome() {
		
		return "customer/main";
	}
}
