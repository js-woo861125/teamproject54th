package ks54team01.customer.register.mapper;



import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.domain.EntMember;



@Mapper
public interface RegisterMapper {
	// 회원조회
	CustomerMember getMemberInfoById(String memberId);
	
	// 입점업체직원정보 등록
	int addEntEmpMember(EntMember member);
	
	// 입점업체대표정보 등록
	int addEntCeoMember(EntMember member);
	
	// 입점업체대표코드 자동생성
	Integer getLastEntCeoNumber();
	
	// 기업정보 등록
	int addCorpMember(CustomerMember member);
	
	// 개인 및 기업회원 정보 등록
	int addCustomerMember(CustomerMember member);
	
	// 일반회원정보 등록
	int addMember(CustomerMember member);
	
	// 공통회원정보 등록
	int addBasicMember(CustomerMember member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);
	

}
