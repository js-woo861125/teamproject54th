package ks54team01.admin.enterprise.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.enterprise.service.AdminEntListService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/enterprise")
public class AdminEntController {
	
	private final AdminEntListService adminEntListService;
	
	@GetMapping("/List")
	// 입점업체 조회
	public String getEntList(Model model) {
		
		List<AdminEntList> EntList = adminEntListService.getEntList();
	    
		model.addAttribute("title", "입점업체 목록");
		model.addAttribute("EntList", EntList);
	    
	    return "admin/enterprise/enterpriseListView";
	    
	}
	@GetMapping("/Detail")
	public String getEntDetail(Model model) {
		
		
		return "admin/enterprise/enterpriseDetailView";
	}
}
