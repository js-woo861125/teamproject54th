package ks54team01.admin.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.mypage.domain.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminMypageController {

	@GetMapping("/myAccount")
	public String myAccountPage(HttpSession session, Model model) {
		
		model.addAttribute("title", "내 프로필");
		
		Object loginInfo = session.getAttribute("memberInfo");
		
	    if (loginInfo == null) {
	        return "redirect:/admin/login"; 
	    }
	    
	    Admin memberInfo = (Admin) loginInfo;
	    
	    log.info("회원정보 :{}", memberInfo);
	    
	    String managerPhone = memberInfo.getManagerPhone();
		String[] managerPhoneArray = managerPhone.split("-");
	    
	    model.addAttribute("memberInfo", memberInfo);
	    model.addAttribute("managerPhone1", managerPhoneArray[0]);
		model.addAttribute("managerPhone2", managerPhoneArray[1]);
		model.addAttribute("managerPhone3", managerPhoneArray[2]);
		
	    return "customer/myPage/myAccountView";
	}
}
