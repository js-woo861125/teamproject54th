package ks54team01.enterprise.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.EntMember;
import ks54team01.customer.member.mapper.MemberMapper;
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
	private final MemberMapper memberMapper;
	private final EnterpriseAccountMapper enterpriseAccountMapper;
	private final PasswordEncoder passwordEncoder;
	

	
	/**
	 * 입점업체 정보 수정
	 */
	@Override
    public boolean modifyEntInfo(EntMember modifyMember, String memberType, String  newPw) {
		Map<String, Object> commonInfoMap = new HashMap<>();
		commonInfoMap.put("memberId", modifyMember.getMemberId());
		
		log.info("정보수정시도 : {}", modifyMember.getMemberId());
		
		if (newPw != null && !newPw.trim().isEmpty()) {
	    	String encodedPw = passwordEncoder.encode(newPw);
	    	commonInfoMap.put("newPw", encodedPw);
	    }
		
        int updatedCount = 0;
        updatedCount += memberMapper.modifyCommonInfo(commonInfoMap);

        if ("입점업체 대표".equals(memberType)) {
            updatedCount += enterpriseAccountMapper.modifyEntEmp(modifyMember);
            updatedCount += enterpriseAccountMapper.modifyEntCeo(modifyMember);

        } else if ("입점업체 직원".equals(memberType)) {
            updatedCount += enterpriseAccountMapper.modifyEntEmp(modifyMember);
        }

        return  updatedCount > 0;
    }
    
	/**
	 * 비밀번호 일치여부 체크
	 */
	@Override
	public boolean isPwCheck(String memberId, String memberPw) {
		CommonMember memberInfo = loginMapper.getMemberInfoById(memberId);
		String encodedPw = memberInfo.getMemberPw(); 
		return passwordEncoder.matches(memberPw, encodedPw);
	}
	 
	
	/**
	 * 입점업체 정보 조회(로그인)
	 */
	@Override
		public EntMember getEntInfoById(String loginId) {
			return loginMapper.getEntMemberInfoById(loginId);
		}
}
