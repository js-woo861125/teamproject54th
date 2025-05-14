package ks54team01.customer.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.register.domain.CommonMember;
import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.domain.EntMember;
import ks54team01.customer.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/register")
@Slf4j
public class RegisterController {
	
	private final RegisterService registerService;
	
	@PostMapping("/entRegister")
	public String addEntMember(EntMember memberInfo, HttpSession session) {
		// 세션에서 공통등록정보 가져온 후 memberInfo로 저장
		CommonMember common = (CommonMember) session.getAttribute("memberInfo");
		
	    if (common != null) {
	        memberInfo.setMemberId(common.getMemberId());
	        memberInfo.setMemberPw(common.getMemberPw());
	        memberInfo.setMemberType(common.getMemberType());
	    }
	    
	    // 자동 생성된 entCeoNo
	    String entCeoNo = registerService.generateEntCeoNo(); 
	    memberInfo.setEntCeoNo(entCeoNo);
	    
	    // 세션에 entCeoNo 저장
	    session.setAttribute("memberInfo", memberInfo);
	    
	    log.info("회원 등록 시작: {}", memberInfo);
	    
	    registerService.addEntCeoMember(memberInfo);

	    log.info("회원 등록 완료: {}", memberInfo);

	    return "redirect:/customer/login/memberLogin";
	}
	
	
	@GetMapping("/entRegister") 
	public String getEntRegister(HttpSession session, Model model) {
		// 세션에서 공통등록정보 가져온 후 CustomerMember로 복사
		CommonMember common = (CommonMember) session.getAttribute("memberInfo");
		EntMember ent = new EntMember(common);
		
		if (common != null) {
			ent.setMemberId(common.getMemberId());
			ent.setMemberPw(common.getMemberPw());
			ent.setMemberType(common.getMemberType());
	    }
		
		log.info("전달받은 공통등록 정보: {}", ent);
		
		model.addAttribute("title", "입점업체");
		model.addAttribute("memberInfo", ent);
		
		return "customer/register/entRegisterView";
		
	}
	
	
	@PostMapping("/customerRegister")
	public String addCustomerMember(CustomerMember memberInfo, HttpSession session) {
	    // 세션에서 공통등록정보 가져온 후 memberInfo로 저장
		CommonMember common = (CommonMember) session.getAttribute("memberInfo");
		
		if (common != null) {
			memberInfo.setMemberId(common.getMemberId());
			memberInfo.setMemberPw(common.getMemberPw());
	    }

	    log.info("회원 등록 시작: {}", memberInfo);

	    registerService.addCustomerMember(memberInfo);

	    log.info("회원 등록 완료: {}", memberInfo);

	    return "redirect:/customer/login/memberLogin";
	}
	
	
	@GetMapping("/customerRegister")
	public String getCustomerRegister(HttpSession session, Model model) {
		// 세션에서 공통등록정보 가져온 후 CustomerMember로 복사
		CommonMember common = (CommonMember) session.getAttribute("memberInfo");
		
		if (common == null) {
	        return "redirect:/customer/register/memberRegister";
	    }
		
		CustomerMember customer = new CustomerMember(common);
		
		log.info("전달받은 공통등록 정보: {}", customer);
		
		model.addAttribute("title", "일반/기업회원");
	    model.addAttribute("customerMemberInfo", customer);
	    
		return "customer/register/customerRegisterView";
	}
	
	
	@PostMapping("/memberRegister")
	public String submitMemberInfo(@ModelAttribute CommonMember commonMember, HttpSession session) {
		// 세션으로 공통등록정보 전달
		session.setAttribute("memberInfo", commonMember);
		log.info("전달한 공통등록 정보: {}", commonMember);
		
	    
	    // 회원 유형에 따른 페이지 이동
		switch (commonMember.getMemberType()) {
	        case "customer": return "redirect:/customer/register/customerRegister";
	        case "입점업체대표": return "redirect:/customer/register/entRegister";
	        default: return "redirect:/error";
	    }
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
