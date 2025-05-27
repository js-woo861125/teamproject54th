package ks54team01.admin.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.mapper.AdminMemberMapper;
import ks54team01.admin.member.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminMemberServiceImpl implements AdminMemberService {

	// DI 의존성 주입
	private final AdminMemberMapper adminMemberMapper;
	
	/**
	 * 회원 검색
	 */	
	@Override
	public List<AdminMember> getSearchMember(String searchKey, String searchValue, 
	                                         String memberType, String withdrawStatus, String dormantStatus) {

	    if (searchKey == null || (!searchKey.equals("memberId") && !searchKey.equals("memberName"))) {
	        searchKey = "memberId";
	    }

	    if (searchValue != null && searchValue.trim().isEmpty()) {
	        searchValue = null;
	    }

	    if (memberType != null && memberType.trim().isEmpty()) {
	        memberType = null;
	    }

	    if (withdrawStatus != null && withdrawStatus.trim().isEmpty()) {
	        withdrawStatus = null;
	    }
	    if (dormantStatus != null && dormantStatus.trim().isEmpty()) {
	        dormantStatus = null;
	    }

	    List<AdminMember> memberList = adminMemberMapper.getSearchMember(searchKey, searchValue, 
	    																 memberType, withdrawStatus, dormantStatus);

	    return memberList;
	}

	
	
	/**
	 * 회원 로그인 이력 조회
	 */	
	@Override
	public List<AdminLoginHistory> getLoginHistoryList() {
		
		List<AdminLoginHistory> loginHistoryList = adminMemberMapper.getLoginHistoryList();
		
		return loginHistoryList;
	}
	
	/**
	 * 회원 목록 조회
	 */
	@Override
	public List<AdminMember> getMemberList() {
		
		List<AdminMember> memberList = adminMemberMapper.getMemberList();
		
		return memberList;
	}
}
