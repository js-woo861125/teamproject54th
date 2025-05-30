package ks54team01.customer.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.FindMember;
import ks54team01.customer.member.mapper.MemberMapper;
import ks54team01.customer.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final LoginMapper loginMapper;
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 임시비밀번호 업데이트
	 */
	@Override
	public void updateRandomPw(String memberId, String tempPw) {
		String encodedPw = passwordEncoder.encode(tempPw);
	   
	    memberMapper.updatePassword(memberId, encodedPw);
		
	}
	
	/**
	 * 비밀번호 찾기
	 */
	@Override
	public FindMember findMemberPwByInfo(String memberId, String memberEmail, String memberType) {
		return memberMapper.findMemberPwByInfo(memberId, memberEmail, memberType);
	}
	
	/**
	 * 아이디 찾기
	 */
	@Override
	public FindMember findMemberIdByInfo(String memberName, String memberPhone, String memberEmail, String memberType) {
		return memberMapper.findMemberIdByInfo(memberName, memberPhone, memberEmail, memberType);
	}
	
	
	/**
	 * 회원탈퇴
	 */
	@Override
	public boolean customerLeave(String memberType, String memberId) {
		 int result = 0;
		 log.info("탈퇴시작: {}", memberId);

		    switch (memberType) {
		        case "개인고객":
		            result += memberMapper.deactivateCustomerMember(memberId);
		            break;
		        case "기업고객":		        	
		        	result += memberMapper.deactivateCorpMember(memberId);
		        	result += memberMapper.deactivateCustomerMember(memberId);
		            break;
		        default:
		            log.warn("알 수 없는 회원 아이디: {}, 회원유형 : {}", memberId, memberType);
		            return false;
		    }

		    result += memberMapper.deactivateCommonMember(memberId);
		    
		    return result > 1;
		}
		
	
	
	
	/**
	 * 처리 진행중인 상태 여부 조회(탈퇴)
	 */
	@Override
	public boolean checkStatus(String memberId) {
		
        int resultCount = memberMapper.resultCountById(memberId);
        if (resultCount > 0) return true;

		return false;
	}
	

	
	/**
	 * 회원정보 수정
	 */
	@Override
    public boolean modifyCustomerInfo(CustomerMember modifyMember, String  newPw) {
		Map<String, Object> commonInfoMap = new HashMap<>();
		commonInfoMap.put("memberId", modifyMember.getMemberId());

	    if (newPw != null && !newPw.trim().isEmpty()) {
	    	String encodedPw = passwordEncoder.encode(newPw);
	    	commonInfoMap.put("newPw", encodedPw);
	    }
		
		int commonUpdateCount = memberMapper.modifyCommonInfo(commonInfoMap);
		
		int customerUpdateCount = memberMapper.modifyCustomerInfo(modifyMember);

	   // 기업고객 추가정보
	    int corpUpdateCount  = 1;
	    if ("기업고객".equals(modifyMember.getMemberType())) {
	    	corpUpdateCount  = memberMapper.modifyCorpInfo(modifyMember);
	    }

	    return commonUpdateCount > 0 && customerUpdateCount > 0 && corpUpdateCount  > 0;
    }
	
	/**
	 * 비밀번호 일치여부 체크
	 */
	@Override
	public boolean isPwCheck(String memberId, String memberPw) {
		CommonMember memberInfo = loginMapper.getMemberInfoById(memberId);
		String encodedPw = memberInfo.getMemberPw(); 
		return passwordEncoder.matches(memberPw, encodedPw);
	}
	
	
	/**
	 * 고객유형별 개인정보 조회
	 */
	@Override
	public CustomerMember getCorpInfoById(String memberId) {
		return memberMapper.getCorpInfoById(memberId);
	}
	
	@Override
	public CustomerMember getCustomerInfoById(String memberId) {
		return memberMapper.getCustomerInfoById(memberId);
	}


	
}


