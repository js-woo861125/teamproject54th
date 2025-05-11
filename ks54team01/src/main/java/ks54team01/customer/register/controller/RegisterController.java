package ks54team01.customer.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/register")
@Slf4j
public class RegisterController {
	
	private final RegisterService registerService;
	
	
	
	@GetMapping("/entRegister")
	public String getEntRegister(Model model) {
		
		model.addAttribute("title", "입점업체");
		
		return "customer/register/entRegisterView";
		
	}
	
	@PostMapping("/customerRegister")
	public String addCustomerMember(CustomerMember memberInfo, HttpSession session) {
	    // 세션에서 memberId 가져오기
	    CustomerMember sessionMember = (CustomerMember) session.getAttribute("memberInfo");
	    if (sessionMember != null) {
	        memberInfo.setMemberId(sessionMember.getMemberId());
	        memberInfo.setMemberPw(sessionMember.getMemberPw());
	    }

	    log.info("회원 등록 시작: {}", memberInfo);

	    registerService.addBasicMember(memberInfo);

	    log.info("회원 등록 완료");

	    return "redirect:/customer/login/memberLogin";
	}
	
	
	@GetMapping("/customerRegister")
	public String getCustomerRegister(HttpSession session, Model model) {
		
		model.addAttribute("title", "일반/기업회원");
		CustomerMember memberInfo = (CustomerMember) session.getAttribute("memberInfo");
		
		log.info("전달받은 공통등록 정보: {}", memberInfo);
		
	    model.addAttribute("memberInfo", memberInfo);
	    
		return "customer/register/customerRegisterView";
	}
	
	
	@PostMapping("/memberRegister")
	public String submitBasicMemberInfo(@ModelAttribute CustomerMember memberInfo, HttpSession session) {
		 
		log.info("전달한 공통등록 정보: {}", memberInfo);
		
	    session.setAttribute("memberInfo", memberInfo);
	    
	    // 회원 유형에 따른 페이지 이동
	    if ("customer".equals(memberInfo.getMemberType())) {
	        return "redirect:/customer/register/customerRegister";
	    } else if ("ent".equals(memberInfo.getMemberType())) {
	        return "redirect:/customer/register/entRegister";
	    }
	    return "redirect:/error";
	}
	
	
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(String memberId){
		boolean isDuplicate = false;
		
		log.info("체크아이디: {}", memberId);
		
		isDuplicate = registerService.isIdCheck(memberId);
		
		return isDuplicate;
	}
	
	
	@GetMapping("/memberRegister")
	public String getMemberRegister(Model model) {
		
		model.addAttribute("title", "회원가입");
		
		return "customer/register/memberRegisterView";
	}
	
	
	
}
