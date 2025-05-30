package ks54team01.admin.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.domain.AdminMemberDetail;
import ks54team01.system.util.Pageable;

@Mapper
public interface AdminMemberMapper {
	// 회원 로그인 이력 검색
	List<AdminLoginHistory> getSearchLoginHistoryList(String searchKey, String searchValue, String memberType, String withdrawStatus, String dormantStatus);
	
	// 회원 로그인이력 총 row 갯수 조회
	int getLoginHistoryCount();
	
	// 회원 로그인 이력 조회
	List<AdminLoginHistory> getLoginHistoryList(Pageable pageable);
	
	// 회원 검색
	List<AdminMember> getSearchMember(String searchKey, String searchValue, String memberType, String withdrawStatus, String dormantStatus);
	
	// 입점업체 상세정보 조회
	AdminMemberDetail getEntMemberDetail(String memberId);
	
	// 입점업체 상세정보 조회
	AdminMemberDetail getManageMemberDetail(String memberId);
	
	// 기업고객 상세정보 조회
	AdminMemberDetail getCorpMemberDetail(String memberId);
	
	// 개인고객 상세정보 조회
	AdminMemberDetail getCustomerMemberDetail(String memberId);
	
	// 회원 로그인이력 총 row 갯수 조회
	int getMemberListCount();
	
	// 회원 목록 조회
	List<AdminMember> getMemberList();


}
