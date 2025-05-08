package ks54team01.customer.member.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ks54team01.customer.member.mapper.CustomerMemberMapper;
import ks54team01.customer.member.service.CustomerMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerMemberServiceImpl implements CustomerMemberService{
	
	private final CustomerMemberMapper customerMemberMapper;
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return customerMemberMapper.isIdCheck(memberId);
	}
	

	
}
