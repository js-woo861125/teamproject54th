package ks54team01.customer.member.mapper;



import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CustomerMember;



@Mapper
public interface CustomerMemberMapper {
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
