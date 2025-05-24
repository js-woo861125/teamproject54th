package ks54team01.enterprise.account.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.manage.domain.Admin;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/enterprise")
@Slf4j
public class EnterpriseAccountController {

	
	@GetMapping("/myAccount")
	public String myAccountPage(HttpSession session, Model model) {
		
		model.addAttribute("title", "내 프로필");
		
		Object loginObj = session.getAttribute("loginMember");
		
	    if (loginObj == null || !(loginObj instanceof CustomerMember)) {
	        return "redirect:/customer/login/memberLogin"; 
	    }
	    
	    EntMember memberInfo = (EntMember) loginObj;
	    String loginId = memberInfo.getMemberId();
	    
	    log.info("회원정보 :{}", memberInfo); 
	    
	    String entPhone = memberInfo.getEntCeoPhone();
	    String entEmail = memberInfo.getEntCeoEmail();
		String[] entPhoneArray = entPhone.split("-");
	    
	    model.addAttribute("memberInfo", memberInfo);
	    model.addAttribute("custPhone1", entPhoneArray[0]);
		model.addAttribute("custPhone2", entPhoneArray[1]);
		model.addAttribute("custPhone3", entPhoneArray[2]);
		model.addAttribute("custEmail", entEmail);

	 

	    return "customer/myPage/myAccountView";
	}
}
