package ks54team01.admin.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;

@Mapper
public interface AdminMemberMapper {

	// 회원 로그인 이력 조회
	List<AdminLoginHistory> getLoginHistoryList();
	
	// 회원 목록 조회
	List<AdminMember> getMemberList();
}
