package ks54team01.enterprise.management.service;

import java.util.List;

import ks54team01.customer.member.domain.EntMember;

public interface EnterpriseManagementService {
	// 직원 등록
	void addEntEmpMember(EntMember entMember);
	
	// 직원 목록 조회
	List<EntMember> getEmployeeList(String entCeoNo);

	
}
