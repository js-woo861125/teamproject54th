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
import ks54team01.customer.member.domain.EntMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/login")
@Slf4j
public class LoginController {

	public final LoginService loginService;
	
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
		 
	        session.invalidate(); 
	        
	        return "redirect:/";  
	    }
	 
	
	 @PostMapping("/memberLogin")
	 public String memberLoginPro(@RequestParam String memberId,
	                              @RequestParam String memberPw,
	                              HttpSession session) {

	     log.info("로그인 시도: memberId={}", memberId);

	     Map<String, Object> loginResult = loginService.matchMember(memberId, memberPw);

	     if (loginResult == null) {
	         log.warn("로그인 실패: 로그인 결과가 null (memberId={})", memberId);
	         return "redirect:/customer/login/memberLogin?error=true";
	     }
	     
	     if ("Y".equals(loginResult.get("memberWithdrawStatus"))) {
	    	 log.warn("탈퇴 회원 로그인 시도: {}", memberId);
	    	 return "redirect:/customer/login/memberLogin?withdrawn=true";
	     }
	     
	     if (memberId == null || loginResult.get("memberInfo") == null) {
	         log.warn("로그인 실패: 존재하지 않거나 비밀번호 불일치: {}", memberId);
	         return "redirect:/customer/login/memberLogin?error=true";
	     }


	     // 세션 저장
	     String loginMemberType = (String) loginResult.get("memberType");
	     session.setAttribute("loginId", memberId);
	     session.setAttribute("loginMemberType", loginMemberType);

	     log.info("로그인 성공: memberId: {} memberType: {} loginResult: {}", memberId, loginMemberType, loginResult);

	     if ("입점업체 대표".equals(loginMemberType) || "입점업체 직원".equals(loginMemberType)) {
	    	 EntMember entCeo = (EntMember) loginResult.get("memberInfo");
	    	 //손 내가 추가 했어!
	    	 log.info("memberLoginPro getMemberId{}",entCeo.getMemberId());
	    	 session.setAttribute("entEmpId", entCeo.getMemberId());
	    	 
	    	 session.setAttribute("entCeoNo", entCeo.getEntCeoNo());
	    	 session.setAttribute("entBrno", entCeo.getEntBrno());
	    	 session.setAttribute("entName", entCeo.getEntName());
	    	 session.setAttribute("entContractStatus", entCeo.getEntContractStatus());
	    	 
	    	 
	         return "redirect:/enterprise";
	     }

	     return "redirect:/";
	 }




	
	
	@GetMapping("/memberLogin")
	public String memberLogin(Model model) {
		
		model.addAttribute("title", "Login");
		
		return "customer/login/memberLoginView";
	}

}
