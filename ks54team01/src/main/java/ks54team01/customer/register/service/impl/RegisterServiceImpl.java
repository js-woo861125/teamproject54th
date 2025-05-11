package ks54team01.customer.register.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.mapper.RegisterMapper;
import ks54team01.customer.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService{
	
	private final RegisterMapper registerMapper;
	

	/**
	 * 회원 정보 조회
	 */
	@Override
	public CustomerMember getMemberInfoById(String memberId) {
		
		CustomerMember memberInfo = registerMapper.getMemberInfoById(memberId);
		
		return memberInfo;
	}
	
	/**
	 * 모든회원정보 등록
	 */
	@Override
	public int addBasicMember(CustomerMember member) {
	    log.info("addBasicMember 시작");

	    // members 테이블에 먼저 insert
	    registerMapper.addBasicMember(member); 
	    
	    // 일반회원정보 customer 테이블에 insert
	    registerMapper.addMember(member);

	    // 기업회원인 경우 corp_customer 테이블에도 insert
	    if (member.getCorpBrno() != null && !member.getCorpBrno().isEmpty()) {
	        registerMapper.addCorpMember(member);
	    }

	    return 1;
	}
	
	/**
	 * 일반회원정보 등록
	 */
	@Override
	public int addMember(CustomerMember member) {
		
		return registerMapper.addMember(member);		
	}
	
	/**
	 * 개인 및 기업회원 정보 등록
	 */
	@Override
	public int addCustomerMember(CustomerMember member) {
		
		return registerMapper.addCustomerMember(member);
		
	}

	/**
	 * 기업정보 등록
	 */
	@Override
	public int addCorpMember(CustomerMember member) {
		
		 return registerMapper.addCorpMember(member);
		
	}
	
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return registerMapper.isIdCheck(memberId);
	}



	
	

	
}
