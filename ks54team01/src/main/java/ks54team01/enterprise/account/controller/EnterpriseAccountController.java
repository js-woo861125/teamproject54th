package ks54team01.enterprise.account.controller;

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
import ks54team01.customer.member.domain.EntMember;
import ks54team01.enterprise.account.service.EnterpriseAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise")
@RequiredArgsConstructor
@Slf4j
public class EnterpriseAccountController {

	private final EnterpriseAccountService enterpriseAccountService;
	
	
	@PostMapping("/myAccount")
	public String modifyEnterpriseMyAccount(@ModelAttribute EntMember modifyMember,
	                                        @RequestParam(value = "newPw", required = false) String newPw,
	                                        HttpSession session,
	                                        RedirectAttributes redirectAttributes) {

	    String loginId = (String) session.getAttribute("loginId");
	    String loginMemberType = (String) session.getAttribute("loginMemberType");

	    if (loginId == null) {
	        return "redirect:/customer/login/memberLogin"; 
	    }

	    modifyMember.setMemberId(loginId);
	    log.info("입점업체 회원수정 시작: {}", loginId);
	    
	    if ("입점업체 대표".equals(loginMemberType)) {
	        enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType, newPw);
	    } else if ("입점업체 직원".equals(loginMemberType)) {
	        enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType, newPw);
	    }
	    
	    boolean result = enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType, newPw);
	    log.info("정보수정 성공 여부: {}", result);	    
	    
	    if (result) {
	        redirectAttributes.addFlashAttribute("message", "회원정보가 성공적으로 수정되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("error", "회원정보 수정에 실패했습니다.");
	    }

	    return "redirect:/enterprise/myAccount";
	}

	
	@PostMapping("/pwCheck")
	@ResponseBody
	public Map<String, Boolean> pwCheck(@RequestParam String memberId, 
										@RequestParam String memberPw){
		
		log.info("비밀번호 체크 시도 :memberId={}, memberPw={}", memberId, memberPw);
		
	    boolean isMatched = enterpriseAccountService.isPwCheck(memberId, memberPw);
	    
	    return Map.of("match", isMatched);
	}
	
	
	@GetMapping("/myAccount")
	public String enterpriseMyAccountPage(HttpSession session, Model model) {

	    String loginId = (String) session.getAttribute("loginId");
	    String loginMemberType = (String) session.getAttribute("loginMemberType");

	    if (loginId == null || loginMemberType == null) {
	        return "redirect:/customer/login/memberLogin";
	    }

	    if (!("입점업체 대표".equals(loginMemberType) || "입점업체 직원".equals(loginMemberType))) {
	        return "redirect:/";
	    }

	    EntMember entMemberInfo = enterpriseAccountService.getEntInfoById(loginId);
	    String phone = entMemberInfo.getEntEmpPhone();
	    model.addAttribute("entMemberInfo", entMemberInfo);
	    model.addAttribute("memberType", loginMemberType);
	    
	    if (phone != null && phone.matches("\\d{2,4}-\\d{3,4}-\\d{4}")) {
	        String[] phoneArray = phone.split("-");
	        model.addAttribute("entEmpPhone1", phoneArray[0]);
	        model.addAttribute("entEmpPhone2", phoneArray[1]);
	        model.addAttribute("entEmpPhone3", phoneArray[2]);
	    } else {
	        model.addAttribute("entEmpPhone1", "");
	        model.addAttribute("entEmpPhone2", "");
	        model.addAttribute("entEmpPhone3", "");
	    }

	    return "enterprise/myPage/entMyAccountView";
	}

}
