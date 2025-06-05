package ks54team01.admin.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.domain.AdminMemberDetail;
import ks54team01.system.util.Pageable;

@Mapper
public interface AdminMemberMapper {
	// 회원 로그인이력 검색 총 row 갯수 조회
	int getSearchLoginHistoryCount(Map<String, Object> searchLoginMap);
	
	// 회원 로그인 이력 검색
	List<AdminLoginHistory> getSearchLoginHistoryList(Map<String, Object> searchLoginMap);
	
	// 회원 로그인이력 총 row 갯수 조회
	int getLoginHistoryCount();
	
	// 회원 로그인 이력 조회
	List<AdminLoginHistory> getLoginHistoryList(Pageable pageable);
	
	// 입점업체 상세정보 조회
	AdminMemberDetail getEntMemberDetail(String memberId);
	
	// 입점업체 상세정보 조회
	AdminMemberDetail getManageMemberDetail(String memberId);
	
	// 기업고객 상세정보 조회
	AdminMemberDetail getCorpMemberDetail(String memberId);
	
	// 개인고객 상세정보 조회
	AdminMemberDetail getCustomerMemberDetail(String memberId);
	
	// 회원 검색 총 row 갯수 조회
	int getSearchMemberListCount(Map<String, Object> searcMemberhMap);
	
	// 회원 검색
	List<AdminMember> getSearchMember(Map<String, Object> searcMemberhMap);
	
	// 회원 목록 총 row 갯수 조회
	int getMemberListCount();
	
	// 회원 목록 조회
	List<AdminMember> getMemberList(Pageable pageable);


}
