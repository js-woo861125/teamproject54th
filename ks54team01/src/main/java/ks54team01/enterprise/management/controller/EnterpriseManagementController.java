package ks54team01.enterprise.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String addEmployee(Model model) {
		
		model.addAttribute("title", "직원등록");
		
		return "enterprise/management/addEmployeeView";
	}

	@GetMapping("/employeeList")
	public String employeeList(Model model) {
		
		model.addAttribute("title", "직원목록");
		
		return "enterprise/management/employeeListView";
	}
}
