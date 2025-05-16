package ks54team01.customer.login.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/login")
@Slf4j
public class LoginController {

	public final LoginService loginService;
	
	@PostMapping("/memberLogin")
	public String login(@RequestParam String memberId, @RequestParam String memberPw, HttpSession session) {
		
		log.info("로그인 시도: memberId={}", memberId);

	    Map<String, Object> loginResult = loginService.login(memberId, memberPw);

	    if (loginResult == null || loginResult.get("memberInfo") == null) {
	    	log.warn("로그인 실패: 존재하지 않거나 비밀번호 불일치 - memberId={}", memberId);
	        return "redirect:/memberLogin?error=true";
	    }

	    String memberType = (String) loginResult.get("memberType");
	    session.setAttribute("memberType", loginResult.get("memberType"));
	    session.setAttribute("loginMember", loginResult.get("memberInfo"));
	    
	    
	    if ("입점업체 대표".equals(memberType) || "입점업체 직원".equals(memberType)) {
	        return "redirect:/enterprise";
	    }

	    log.info("로그인 성공: memberId={} memberType={}", memberId, memberType);

	   

	    return "redirect:/";
	}


	
	
	@GetMapping("/memberLogin")
	public String getMemberLogin(Model model) {
		
		model.addAttribute("title", "Login");
		
		return "customer/login/memberLoginView";
	}

}
