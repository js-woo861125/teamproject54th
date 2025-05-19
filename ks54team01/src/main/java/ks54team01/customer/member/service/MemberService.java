package ks54team01.customer.member.service;

import org.springframework.stereotype.Service;

import ks54team01.customer.member.domain.CustomerMember;

@Service
public interface MemberService {
	// 회원정보 수정
	boolean modifyCustomerInfo(CustomerMember modifyMember);
	
	// 기업고객 정보 조회
	CustomerMember getCorpInfoById(String loginId);
	
	// 개인 및 기업고객 정보 조회
	CustomerMember getCustomerInfoById(String loginId);

}
