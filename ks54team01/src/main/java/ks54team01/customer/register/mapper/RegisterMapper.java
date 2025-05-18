package ks54team01.customer.register.mapper;



import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;



@Mapper
public interface RegisterMapper {
	// 입점업체직원정보 등록
	int addEntEmpMember(EntMember member);
	
	// 입점업체대표 직원테이블에 등록
	int addEntMember(EntMember member);
	
	// 입점업체대표정보 등록
	int addEntCeoMember(EntMember member);

	// 기업고객 추가정보 등록
	int addCorpMember(CustomerMember member);
	
	// 개인 및 기업고객 정보 등록
	int addCustomerMember(CustomerMember member);
	
	// 고객공통정보 등록
	int addCommonMember(CommonMember member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);
}
