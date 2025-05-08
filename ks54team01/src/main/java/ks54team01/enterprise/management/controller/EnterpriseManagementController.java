package ks54team01.enterprise.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprise/management")
public class EnterpriseManagementController {

	@GetMapping("/employeeList")
	public String employeeList() {
		
		return "enterprise/management/employeeListView";
	}
}
