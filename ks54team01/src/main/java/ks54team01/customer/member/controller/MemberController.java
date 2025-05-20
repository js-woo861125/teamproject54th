package ks54team01.customer.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/customerLeave")
	public String getcustomerLeave(Model model) {
		
		model.addAttribute("title", "회원탈퇴");
		
		return "customer/myPage/customerLeaveView";
		
	}
	
	@PostMapping("/myAccount")
	public String modifyMyAccount(@ModelAttribute CustomerMember modifyMember,
			 					  @RequestParam(value = "newPw", required = false) String newPw,
	                              HttpSession session,
	                              RedirectAttributes redirectAttributes) {

	    Object loginObj = session.getAttribute("loginMember");
	    if (loginObj == null || !(loginObj instanceof CommonMember)) {
	        return "redirect:/customer/login/memberLogin";
	    }

	    String loginId = ((CommonMember) loginObj).getMemberId();
	    modifyMember.setMemberId(loginId);

	    log.info("회원수정 시작: {}", loginId);
	    boolean result = memberService.modifyCustomerInfo(modifyMember, newPw);

	    if (result) {
	        redirectAttributes.addFlashAttribute("message", "회원정보가 성공적으로 수정되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("error", "회원정보 수정에 실패했습니다.");
	    }

	    return "redirect:/customer/member/myAccount";
	}

	
	@PostMapping("/pwCheck")
	@ResponseBody
	public Map<String, Boolean> pwCheck(@RequestParam String memberId, @RequestParam String memberPw){
		log.info("비밀번호 체크 시도 :memberId={}, memberPw={}", memberId, memberPw);
		
	    boolean isMatch = memberService.isPwCheck(memberId, memberPw);
	    
	    return Map.of("match", isMatch);
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
	    log.info("개인고객정보 :{}", memberInfo);
	    
	    String custPhone = memberInfo.getCustPhone();
	    String custEmail = memberInfo.getCustEmail();
		String[] custPhoneArray = custPhone.split("-");
	    
	    model.addAttribute("memberInfo", memberInfo);
	    model.addAttribute("custPhone1", custPhoneArray[0]);
		model.addAttribute("custPhone2", custPhoneArray[1]);
		model.addAttribute("custPhone3", custPhoneArray[2]);
		model.addAttribute("custEmail", custEmail);

	    if ("기업고객".equals(memberInfo.getMemberType())) {
	    	CustomerMember corpInfo = memberService.getCorpInfoById(loginId);
	    	memberInfo.setCorpName(corpInfo.getCorpName());
	    	memberInfo.setCorpBrno(corpInfo.getCorpBrno());
	    	log.info("기업고객정보 :{}", corpInfo);
	        model.addAttribute("corpInfo", corpInfo);
	    }

	    return "customer/myPage/myAccountView";
	}
	
}
