package ks54team01.customer.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.service.CustomerMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/member")
@Slf4j
public class CustomerMemberController {
	
	private final CustomerMemberService customerMemberService;
	
	
	@GetMapping("/forgotId")
	public String getforgotId(Model model) {
		
		model.addAttribute("title", "아이디찾기");
		
		return "customer/member/forgotIdView";
		
	}
	
	@GetMapping("/forgotPw")
	public String getforgotPw(Model model) {
		
		model.addAttribute("title", "비밀번호찾기");
		
		return "customer/member/forgotPwView";
		
	}
	
	
	@GetMapping("/entRegister")
	public String getEntRegister(Model model) {
		
		model.addAttribute("title", "입점업체");
		
		return "customer/member/entRegisterView";
		
	}
	
	@PostMapping("/addmember")
	public String addMember(CustomerMember memberInfo) {
		
		log.info("회원등록: {}", memberInfo);
		
		customerMemberService.addMember(memberInfo);
		
		return "redirect:/customer/member/memberLogin";
	}
	
	
	@GetMapping("/customerRegister")
	public String getCustomerRegister(Model model) {
		
		model.addAttribute("title", "일반/기업회원");
		
		return "customer/member/customerRegisterView";
	}
	
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(String memberId){
		boolean isDuplicate = false;
		
		log.info("체크아이디: {}", memberId);
		
		isDuplicate = customerMemberService.isIdCheck(memberId);
		
		return isDuplicate;
	}
	
	
	@GetMapping("/memberRegister")
	public String getMemberRegister(Model model) {
		
		model.addAttribute("title", "회원가입");
		
		return "customer/member/memberRegisterView";
	}
	
	
	@GetMapping("/memberLogin")
	public String getMemberLogin(Model model) {
		
		model.addAttribute("title", "Login");
		
		return "customer/member/memberLoginView";
	}
	
}
