package ks54team01.admin.member.service;

import java.util.List;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;

public interface AdminMemberService {

	// 회원 로그인 이력 조회
	List<AdminLoginHistory> getLoginHistoryList();
	
	// 회원 목록 조회
	List<AdminMember> getMemberList();
	
}
