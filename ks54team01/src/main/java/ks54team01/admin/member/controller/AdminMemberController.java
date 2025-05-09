package ks54team01.admin.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.service.AdminMemberService;
import ks54team01.admin.member.service.impl.AdminMemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
@Slf4j
public class AdminMemberController {

	// DI 의존성 주입
	private final AdminMemberService adminMemberService;
	
	@GetMapping("/loginHistoryList")
	public String getLoginHistoryList(Model model) {
		
		List<AdminLoginHistory> loginHistoryList = adminMemberService.getLoginHistoryList();
		log.info("loginHistoryList: {}", loginHistoryList);
		
		model.addAttribute("title", "로그인 내역조회");
		model.addAttribute("loginHistoryList", loginHistoryList);
		
		return "admin/member/loginHistoryListView";
	}
	
	@GetMapping("/memberList") 
	public String getMemberList(Model model) {
		
		List<AdminMember> memberList = adminMemberService.getMemberList();
		log.info("memberList: {}", memberList);
		
		model.addAttribute("title", "회원목록");
		model.addAttribute("memberList", memberList);
		
		
		return "admin/member/memberListView";
	}
	
	
}