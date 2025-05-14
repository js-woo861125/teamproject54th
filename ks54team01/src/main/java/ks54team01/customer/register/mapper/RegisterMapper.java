package ks54team01.customer.register.mapper;



import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.register.domain.CommonMember;
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

	// 기업회원 추가정보 등록
	int addCorpMember(CustomerMember member);
	
	// 개인 및 기업회원 정보 등록
	int addCustomerMember(CustomerMember member);
	
	// 공통회원정보 등록
	int addCommonMember(CommonMember member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);

	

}
