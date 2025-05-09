package ks54team01.customer.member.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.member.domain.CustomerMember;
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
	 * 공통회원정보 등록
	 */
	@Override
	public void addBasicMember(CustomerMember member) {
		 // 1. 공통 회원 정보 등록
	    addMember(member);

	    // 2. 기업회원 여부 판단 (사업자번호가 있을 경우)
	    if (member.getCorpBrno() != null && !member.getCorpBrno().isEmpty()) {
	        addCorpMember(member);       // 기업회원 추가정보 등록
	    }

	    // 3. 개인 및 기업 공통정보 등록
	    addCustomerMember(member);
	   
	}
	
	/**
	 * 일반회원정보 등록
	 */
	@Override
	public int addMember(CustomerMember member) {
		
		return customerMemberMapper.addMember(member);		
	}
	
	/**
	 * 개인 및 기업회원 정보 등록
	 */
	@Override
	public int addCustomerMember(CustomerMember member) {
		
		return customerMemberMapper.addCustomerMember(member);
		
	}

	/**
	 * 기업정보 등록
	 */
	@Override
	public int addCorpMember(CustomerMember member) {
		
		 return customerMemberMapper.addCorpMember(member);
		
	}
	
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return customerMemberMapper.isIdCheck(memberId);
	}



	
	

	
}
