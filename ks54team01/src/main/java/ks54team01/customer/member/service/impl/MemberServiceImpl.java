package ks54team01.customer.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.mapper.MemberMapper;
import ks54team01.customer.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper memberMapper;
	

	/**
	 * 회원탈퇴
	 */
	@Override
	public boolean customerLeave(String memberType, String memberId) {
		 int result = 0;

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
	    	commonInfoMap.put("newPw", newPw);
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
		
		return memberMapper.isPwCheck(Map.of("memberId", memberId, "memberPw", memberPw));
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


