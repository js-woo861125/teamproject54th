package ks54team01.customer.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.login.service.LoginService;
import ks54team01.customer.register.domain.CustomerMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/login")
@Slf4j
public class LoginController {

	public final LoginService loginService;
	

	@PostMapping("/memberLogin")
	public String getMemberLogin(String memberId, String memberPw, HttpSession session, Model model) {
		
		var resultMap = loginService.matchMember(memberId, memberPw);
		boolean isMatched = (boolean) resultMap.get("isMatched");

	    if (isMatched) {
	        CustomerMember memberInfo = (CustomerMember) resultMap.get("memberInfo");
	        session.setAttribute("SID", memberInfo.getMemberId());
	        session.setAttribute("STYPE", memberInfo.getMemberType());
	        return "redirect:/main";
	    } else {
	        model.addAttribute("loginFail", true);
	        return "login/memberLoginView"; 
	    }
	}
	
	
	@GetMapping("/memberLogin")
	public String getMemberLogin(Model model) {
		
		model.addAttribute("title", "Login");
		
		return "customer/login/memberLoginView";
	}

}
