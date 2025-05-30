package ks54team01.admin.member.service;


import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.domain.AdminMemberDetail;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;

public interface AdminMemberService {
	// 회원 로그인 이력 검색
	PageInfo<AdminLoginHistory> getSearchLoginHistoryList(Pageable pageable, String searchKey, String searchValue, String memberType, String withdrawStatus, String dormantStatus);
	
	// 회원 로그인 이력 조회
	PageInfo<AdminLoginHistory> getLoginHistoryList(Pageable pageable);
	
	// 회원 검색
	PageInfo<AdminMember> getSearchMember(Pageable pageable, String searchKey, String searchValue, String memberType, String withdrawStatus, String dormantStatus);
	
	// 회원 상세정보 조회
	AdminMemberDetail getMemberDetail(String memberId, String memberType);
	
	// 회원 목록 조회
	PageInfo<AdminMember> getMemberList(Pageable pageable);



	
}
