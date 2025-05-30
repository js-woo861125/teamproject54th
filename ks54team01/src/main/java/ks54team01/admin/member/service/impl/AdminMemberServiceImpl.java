package ks54team01.admin.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.member.domain.AdminLoginHistory;
import ks54team01.admin.member.domain.AdminMember;
import ks54team01.admin.member.domain.AdminMemberDetail;
import ks54team01.admin.member.mapper.AdminMemberMapper;
import ks54team01.admin.member.service.AdminMemberService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
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
	 * 회원 로그인 이력 검색
	 */	
	@Override
	public List<AdminLoginHistory> getSearchLoginHistoryList(String searchKey, String searchValue, String memberType,
														String withdrawStatus, String dormantStatus) {
		if (searchKey == null || (!searchKey.equals("memberId") && !searchKey.equals("memberType"))) {
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

	    List<AdminLoginHistory> loginHistoryList = adminMemberMapper.getSearchLoginHistoryList(searchKey, searchValue, memberType, withdrawStatus, dormantStatus);

	    return loginHistoryList;
	}
	
	
	/**
	 * 회원 로그인 이력 조회
	 */	
	@Override
	public PageInfo<AdminLoginHistory> getLoginHistoryList(Pageable pageable) {
		// 마지막페이지를 구하기 위해 전체 행의 갯수를 조회
		int contentRowCount = adminMemberMapper.getLoginHistoryCount();
		List<AdminLoginHistory> loginHistoryList = adminMemberMapper.getLoginHistoryList(pageable);
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("loginHistoryList: {}", loginHistoryList);
		
		return new PageInfo<>(loginHistoryList, pageable, contentRowCount);
	}
	
	
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

	    List<AdminMember> memberList = adminMemberMapper.getSearchMember(searchKey, searchValue, memberType, withdrawStatus, dormantStatus);

	    return memberList;
	}


	/**
	 * 회원 상세정보 조회
	 */	
	@Override
	public AdminMemberDetail getMemberDetail(String memberId, String memberType) {
		
		 switch (memberType) {
         case "개인고객":
             return adminMemberMapper.getCustomerMemberDetail(memberId);
         case "기업고객":
             return adminMemberMapper.getCorpMemberDetail(memberId);
         case "플랫폼직원":
        	 return adminMemberMapper.getManageMemberDetail(memberId);
         case "입점업체 대표":
         case "입점업체 직원": 
             AdminMemberDetail memberDetail = adminMemberMapper.getEntMemberDetail(memberId);

             if ("입점업체 직원".equals(memberType)) {
            	 memberDetail.setEntBank(null);
            	 memberDetail.setEntBankNum(null);
             }
             return memberDetail;
             
         default:
             throw new IllegalArgumentException("지원하지 않는 회원 유형입니다.");
		 }
		 
	}
	
	
	/**
	 * 회원 목록 조회
	 */
	@Override
	public PageInfo<AdminMember> getMemberList(Pageable pageable) {
		// 마지막페이지를 구하기 위해 전체 행의 갯수를 조회
		int contentRowCount = adminMemberMapper.getMemberListCount();
		List<AdminMember> memberList = adminMemberMapper.getMemberList();
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("memberList: {}", memberList);
		
		return new PageInfo<>(memberList, pageable, contentRowCount);
	}
}
