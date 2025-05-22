package ks54team01.admin.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.login.service.AdminLoginService;
import ks54team01.customer.member.domain.CommonMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminLoginController {
	
	private final AdminLoginService adminLoginService;
	
	@PostMapping("/admin/login")
	public String adminLoginPro(String memberId, String memberPw, HttpSession session) {
		
		var resultMap = adminLoginService.matchMember(memberId, memberPw);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		String viewName = "redirect:/admin/login";
		
		if(isMatched) {
			CommonMember memberInfo = (CommonMember) resultMap.get("memberInfo");
			String sessionId = memberInfo.getMemberId();
			String sessionType = memberInfo.getMemberType();
			
			session.setAttribute("memberId", sessionId);
			session.setAttribute("memberType", sessionType);
			
			viewName = "redirect:/admin";
			
		}
		
		return viewName;
	}
	
	@GetMapping("/admin/login")
	public String adminLogin(Model model) {
		
		model.addAttribute("title", "관리자 로그인");
		
		return "admin/login/loginFormView";
	}
}
