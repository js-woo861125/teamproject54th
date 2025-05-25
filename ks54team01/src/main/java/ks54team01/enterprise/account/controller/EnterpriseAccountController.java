package ks54team01.enterprise.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	        enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType);
	    } else if ("입점업체 직원".equals(loginMemberType)) {
	        enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType);
	    }
	    
	    boolean result = enterpriseAccountService.modifyEntInfo(modifyMember, loginMemberType);

	    if (result) {
	        redirectAttributes.addFlashAttribute("message", "회원정보가 성공적으로 수정되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("error", "회원정보 수정에 실패했습니다.");
	    }

	    return "redirect:/enterprise/myAccount";
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
	    String[] phoneArray = entMemberInfo.getEntEmpPhone().split("-");
	    
	    model.addAttribute("title", "내 프로필");
	    model.addAttribute("entInfo", entMemberInfo);
	    model.addAttribute("memberType", loginMemberType);
        model.addAttribute("entEmpPhone1", phoneArray[0]);
        model.addAttribute("entEmpPhone2", phoneArray[1]);
        model.addAttribute("entEmpPhone3", phoneArray[2]);

	    return "enterprise/myPage/entMyAccountView";
	}

}
