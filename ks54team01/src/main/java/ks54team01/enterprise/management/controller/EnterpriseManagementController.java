package ks54team01.enterprise.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enterprise/management")
public class EnterpriseManagementController {
	
	@GetMapping("/contractInfo")
	public String contractInfo() {
		
		return "enterprise/management/contractInfoView";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee() {
		
		return "enterprise/management/addEmployeeView";
	}

	@GetMapping("/employeeList")
	public String employeeList() {
		
		return "enterprise/management/employeeListView";
	}
}
