package ks54team01.enterprise.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.member.domain.EntMember;
import ks54team01.enterprise.management.domain.EnterpriseContractInfo;
import ks54team01.enterprise.management.mapper.EnterpriseManagementMapper;
import ks54team01.enterprise.management.service.EnterpriseManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterpriseManagementServiceImpl implements EnterpriseManagementService {

	
	private final EnterpriseManagementMapper enterpriseManagerMapper;
	
	/**
	 * 플랫폼 계약정보 조회
	 */
	@Override
	public EnterpriseContractInfo getEntContractInfo(String entCeoNo) {
		
		return enterpriseManagerMapper.getEntContractInfo(entCeoNo);
	}
	
	/**
	 * 직원 등록
	 */
	@Override
	public void addEntEmpMember(EntMember entMember) {
		
		if (entMember.getEntEmpName() == null) entMember.setEntEmpName("010");
	    if (entMember.getEntEmpEmail() == null) entMember.setEntEmpEmail("");
	    if (entMember.getEntEmpPhone() == null) entMember.setEntEmpPhone("");
	    if (entMember.getMemberType() == null) entMember.setMemberType("입점업체 직원");
	    
	    
	    log.info("직원등록시작: {}", entMember);
		enterpriseManagerMapper.addCommonMember(entMember);
		enterpriseManagerMapper.addEntEmpMember(entMember);
	}
	
	/**
	 * 직원 목록 조회
	 */
	@Override
	public List<EntMember> getEmployeeList(String entCeoNo) {
		List<EntMember> employeeList = enterpriseManagerMapper.getEmployeeList(entCeoNo);
		
		return employeeList;
	}
}
