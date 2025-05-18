package ks54team01.customer.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/member")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
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
	
	@GetMapping("/myAccount")
	public String myAccountPage(HttpSession session, Model model) {
		
		model.addAttribute("title", "내 프로필");
		
		Object loginObj = session.getAttribute("loginMember");
	    if (loginObj == null || !(loginObj instanceof CommonMember)) {
	        
	        return "redirect:/customer/login/memberLogin"; 
	    }
	    
	    String loginId = ((CommonMember) loginObj).getMemberId();
	    
	    CustomerMember memberInfo = memberService.getCustomerInfoById(loginId);
	    String custPhone = memberInfo.getCustPhone();
	    
	    
		String[] custPhoneArray = custPhone.split("-");
	    
	    model.addAttribute("memberInfo", memberInfo);
	    model.addAttribute("custPhone1", custPhoneArray[0]);
		model.addAttribute("custPhone2", custPhoneArray[1]);
		model.addAttribute("custPhone3", custPhoneArray[2]);

	    if ("기업".equals(memberInfo.getMemberType())) {
	    	CustomerMember corpInfo = memberService.getCorpInfoById(loginId);
	        model.addAttribute("corpInfo", corpInfo);
	    }

	    return "customer/member/myAccountView";
	}
	
}
