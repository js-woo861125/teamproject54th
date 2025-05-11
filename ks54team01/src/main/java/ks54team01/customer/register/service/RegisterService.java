package ks54team01.customer.register.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.register.domain.CustomerMember;

@Service
@Transactional
public interface RegisterService {
	// 회원조회
	CustomerMember getMemberInfoById(String memberId);
		
	// 공통회원정보 등록
	int addBasicMember(CustomerMember member);
	
	// 일반회원정보 등록
	int addMember(CustomerMember member);
	
	// 개인 및 기업회원 정보 등록
	int addCustomerMember(CustomerMember member);
	
	// 기업정보 등록
	int addCorpMember(CustomerMember member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);

	
}
