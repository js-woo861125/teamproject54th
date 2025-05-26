package ks54team01.enterprise.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.EntMember;

@Mapper
public interface EnterpriseManagementMapper {
	// 직원 등록
	int addEntEmpMember(EntMember entMember);
	
	// 직원 공통정보 등록
	int addCommonMember(EntMember entMember);
	
	// 직원 목록 조회
	List<EntMember> getEmployeeList(String entCeoNo);


}
