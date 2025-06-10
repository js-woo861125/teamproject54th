package ks54team01.admin.manage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.manage.domain.Admin;
import ks54team01.admin.manage.service.AdminManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminManageController {

	private final AdminManageService adminManageService;
	
	@PostMapping("/myAccount")
	public String adminModifyMember(Admin admin, RedirectAttributes reAttr) {
			
			log.info("회원수정: {}", admin);
			
			adminManageService.modifyManagerInfo(admin);
			
			reAttr.addAttribute("memberId", admin.getMemberId());
			
			
			return "redirect:/admin";
		}

	@GetMapping("/myAccount")
	public String adminMyAccountPage(HttpSession session, Model model) {

	    model.addAttribute("title", "내 프로필");

	    Admin admin = (Admin) session.getAttribute("loginAdmin");

	    if (admin == null) return "redirect:/admin/login";

        String[] phoneArray = admin.getManagerPhone().split("-");
        model.addAttribute("adminInfo", admin);
        model.addAttribute("managerPhone1", phoneArray[0]);
        model.addAttribute("managerPhone2", phoneArray[1]);
        model.addAttribute("managerPhone3", phoneArray[2]);

        return "admin/myPage/adminMyAccountView";
	}
	
	
	 @GetMapping("/logout")
	    public String adminLogout(HttpSession session) {
		 
	        session.invalidate(); 
	        
	        return "redirect:/admin/login";  
	    }
	 
	
	@PostMapping("/login")
	public String adminLoginPro(@RequestParam String memberId,
								@RequestParam String memberPw,
								HttpSession session) {
		
		Map<String, Object> loginResult = adminManageService.matchAdmin(memberId, memberPw);
		
		
		  if (loginResult == null || loginResult.get("adminInfo") == null) {
		  log.warn("로그인 실패: 존재하지 않거나 비밀번호 불일치: {}", memberId); return
		  "redirect:/admin/login"; }
		 
		
		Admin adminInfo = (Admin)loginResult.get("adminInfo");
		String adminId = adminInfo.getMemberId();
	    String adminName = adminInfo.getManagerName();
	    String memberType = adminInfo.getMemberType();
	    String entEmpId = adminInfo.getMemberId();
	    
	    // 세션 저장
	    session.setAttribute("loginAdmin", adminInfo);
	    session.setAttribute("adminId", adminId);
	    session.setAttribute("adminName", adminName);
	    session.setAttribute("memberType", memberType);
	    session.setAttribute("entEmpId", adminId);
	    
	    
	    log.info("로그인 성공: adminId={} adminName={}", memberId, adminName);
		    return "redirect:/admin";
		
	}
	
	
	@GetMapping("/login")
	public String adminLogin(Model model) {
		
		model.addAttribute("title", "관리자 로그인");
		
		return "admin/login/loginFormView";
	}
}
