package ks54team01.customer.member.service.impl;

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
    public boolean modifyCustomerInfo(CustomerMember modifyMember) {
		
		int commonUpdateCount = memberMapper.modifyCommonInfo(modifyMember);
		
		int customerUpdateCount = memberMapper.modifyCustomerInfo(modifyMember);

	   // 기업고객 추가정보
	    int corpUpdateCount  = 1;
	    if ("기업고객".equals(modifyMember.getMemberType())) {
	    	corpUpdateCount  = memberMapper.modifyCorpInfo(modifyMember);
	    }

	    return commonUpdateCount > 0 && customerUpdateCount > 0 && corpUpdateCount  > 0;
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


