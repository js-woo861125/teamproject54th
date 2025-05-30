package ks54team01.admin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminCommonController {

	@GetMapping({"","/"})
	public String adminHome() {
	
		return "admin/main";
	}
	

}
