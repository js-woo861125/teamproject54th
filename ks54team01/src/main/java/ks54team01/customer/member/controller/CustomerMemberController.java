package ks54team01.customer.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.mapper.CustomerMemberMapper;
import ks54team01.customer.member.service.CustomerMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/member")
@Slf4j
public class CustomerMemberController {
	
	private final CustomerMemberService customerMemberService;
	private final CustomerMemberMapper customerMemberMapper;
	
	
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
	
	
	@PostMapping("/customerRegister")
	public int addCustomerMember(CustomerMember memberInfo) {
		
		log.info("회원 등록: {}", memberInfo);
		
	    // 1. 공통 회원 등록
	    customerMemberService.addBasicMember(memberInfo);
	    // memberInfo.getMemberId() 에 자동으로 생성된 member_id가 들어 있음

	    // 2. 일반회원 등록
	    memberInfo.setCustId(memberInfo.getMemberId());
	    customerMemberService.addMember(memberInfo);

	    // 3. 기업회원 등록
	    if (memberInfo.getCorpBrno() != null && !memberInfo.getCorpBrno().isEmpty()) {
	    	customerMemberService.addCorpMember(memberInfo);
	    }

	    return 1;
	}
	
	@PostMapping("/memberRegister")
	public String submitBasicMemberInfo(@ModelAttribute CustomerMember memberInfo, HttpSession session) {
		 
		log.info("전달한 공통등록 정보: {}", memberInfo);
		
	    session.setAttribute("memberInfo", memberInfo);
	    
	    // 회원 유형에 따른 페이지 이동
	    if ("customer".equals(memberInfo.getMemberType())) {
	        return "redirect:/customer/member/customerRegister";
	    } else if ("ent".equals(memberInfo.getMemberType())) {
	        return "redirect:/customer/member/entRegister";
	    }
	    return "redirect:/error";
	}
	
	@GetMapping("/customerRegister")
	public String getCustomerRegister(HttpSession session, Model model) {
		
		model.addAttribute("title", "일반/기업회원");
		CustomerMember memberInfo = (CustomerMember) session.getAttribute("memberInfo");
		
		log.info("전달받은 공통등록 정보: {}", memberInfo);
		
	    model.addAttribute("memberInfo", memberInfo);
	    
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
