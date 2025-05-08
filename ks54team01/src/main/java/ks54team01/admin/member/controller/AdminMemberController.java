package ks54team01.admin.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@GetMapping("loginHistoryList")
	public String loginHistoryList() {
		
		return "admin/member/loginHistoryListView";
	}
	
	@GetMapping("/memberList") 
	public String memberList() {
		
		return "admin/member/memberListView";
	}
	
	
}