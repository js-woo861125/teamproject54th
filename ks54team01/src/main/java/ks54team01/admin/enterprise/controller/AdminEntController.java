package ks54team01.admin.enterprise.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.enterprise.service.AdminEntListService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/enterprise")
public class AdminEntController {
	
	@GetMapping("/List")
	public String getEntInfoList(Model model) {
		
	
		
		model.addAttribute("title" , "입점업체 목록");
	
		
		return "admin/enterprise/entListView";
	}
}
