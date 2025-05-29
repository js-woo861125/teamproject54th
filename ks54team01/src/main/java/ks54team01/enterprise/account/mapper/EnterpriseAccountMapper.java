package ks54team01.enterprise.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.EntMember;

@Mapper
public interface EnterpriseAccountMapper {
	// 입점업체대표정보 수정
	int modifyEntCeo(EntMember modifyMember);
	
	// 입점업체직원정보 수정
	int modifyEntEmp(EntMember modifyMember);

}
