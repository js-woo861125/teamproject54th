package ks54team01.customer.register.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;

@Service
@Transactional
public interface RegisterService {
	// 입점업체직원정보 등록
	int addEntEmpMember(EntMember member);
	
	// 입점업체대표정보 등록
	int addEntCeoMember(EntMember member);
	
	// 입점업체대표코드 자동생성
	String generateEntCeoNo();
	
	// 개인 및 기업고객 정보 등록
	int addCustomerMember(CustomerMember member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);

	
}
