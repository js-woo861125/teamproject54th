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


