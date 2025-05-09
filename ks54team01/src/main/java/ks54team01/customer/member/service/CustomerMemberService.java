package ks54team01.customer.member.service;

import org.springframework.stereotype.Service;

import ks54team01.customer.member.domain.CustomerMember;

@Service
public interface CustomerMemberService {
	// 공통회원정보 등록
	void addBasicMember(CustomerMember memberInfo);
	
	// 일반회원정보 등록
	int addMember(CustomerMember memberInfo);
	
	// 개인 및 기업회원 정보 등록
	int addCustomerMember(CustomerMember memberInfo);
	
	// 기업정보 등록
	int addCorpMember(CustomerMember memberInfo);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);

	
}
