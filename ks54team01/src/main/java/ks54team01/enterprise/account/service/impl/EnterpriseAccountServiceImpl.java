package ks54team01.enterprise.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.member.domain.EntMember;
import ks54team01.enterprise.account.mapper.EnterpriseAccountMapper;
import ks54team01.enterprise.account.service.EnterpriseAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterpriseAccountServiceImpl implements EnterpriseAccountService {

	private final LoginMapper loginMapper;
	private final EnterpriseAccountMapper enterpriseAccountMapper;
	

	
	/**
	 * 입점업체 정보 수정
	 */
	@Override
    public boolean modifyEntInfo(EntMember entMember, String memberType) {
        int updatedCount = 0;
        updatedCount += enterpriseAccountMapper.modifyCommonInfo(entMember);

        if ("입점업체 대표".equals(memberType)) {
            updatedCount += enterpriseAccountMapper.modifyEntEmp(entMember);
            updatedCount += enterpriseAccountMapper.modifyEntCeo(entMember);

        } else if ("입점업체 직원".equals(memberType)) {
            updatedCount += enterpriseAccountMapper.modifyEntEmp(entMember);
        }

        return updatedCount > 0;
    }
    
	
	 
	
	/**
	 * 입점업체 정보 조회(로그인)
	 */
	@Override
		public EntMember getEntInfoById(String loginId) {
			return loginMapper.getEntMemberInfoById(loginId);
		}
}
