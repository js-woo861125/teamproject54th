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


