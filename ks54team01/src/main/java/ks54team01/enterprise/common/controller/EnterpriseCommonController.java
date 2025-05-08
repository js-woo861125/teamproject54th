package ks54team01.enterprise.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseCommonController {

	@GetMapping({"","/"})
	public String enterpriseHome() {
		
		return "enterprise/main";
	}
}
