package ks54team01.enterprise.management.service;

import java.util.List;

import ks54team01.customer.member.domain.EntMember;
import ks54team01.enterprise.management.domain.EnterpriseContractInfo;

public interface EnterpriseManagementService {
	// 플랫폼 계약정보 조회
	EnterpriseContractInfo getEntContractInfo(String entCeoNo);
	
	// 직원 등록
	void addEntEmpMember(EntMember entMember);
	
	// 직원 목록 조회
	List<EntMember> getEmployeeList(String entCeoNo);

	
}
