package ks54team01.customer.register.service.impl;



import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.register.domain.CommonMember;
import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.domain.EntMember;
import ks54team01.customer.register.mapper.RegisterMapper;
import ks54team01.customer.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService{
	
	private final RegisterMapper registerMapper;

	/**
	 * 회원 정보 조회
	 */
	@Override
	public CustomerMember getMemberInfoById(String memberId) {
		
		CustomerMember memberInfo = registerMapper.getMemberInfoById(memberId);
		
		return memberInfo;
	}
	
	
	/**
	 * 입점업체직원정보 등록
	 */
	@Override
	public int addEntEmpMember(EntMember member) {
		log.info("입점업체직원 등록 시작");
		
		return registerMapper.addEntEmpMember(member);	
	}
	
	/**
	 * 입점업체대표정보 등록 
	 */
	@Override
	public int addEntCeoMember(EntMember member) {
		
		CommonMember common = new CommonMember();
	    common.setMemberId(member.getMemberId());
	    common.setMemberPw(member.getMemberPw());
	    common.setMemberType(member.getMemberType());

	    // 공통 정보 등록
	    registerMapper.addCommonMember(common);

	    // 입점업체 대표 등록 (ceo, emp 테이블)
	    registerMapper.addEntCeoMember(member);
		registerMapper.addEntMember(member);
	    
	    return 1;
	}
	
	/**
	 * 입점업체대표코드 자동생성
	 */
	public String generateEntCeoNo() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
	}
		

	/**
	 * 개인/기업회원 정보 등록
	 */
	@Override
	public int addCustomerMember(CustomerMember member) {
		
		CommonMember commonMember = new CommonMember();
        commonMember.setMemberId(member.getMemberId());
        commonMember.setMemberPw(member.getMemberPw());
        commonMember.setMemberType(member.getMemberType());
		
	    log.info("개인/기업 회원등록 시작");
	    
	    // members 테이블에 먼저 insert
	    registerMapper.addCommonMember(commonMember); 
	    
	    // 일반회원정보 customer 테이블에 insert
	    registerMapper.addCustomerMember(member);

	    // 기업회원인 경우 corp_customer 테이블에도 insert
	    if ("기업고객".equals(member.getMemberType())) {
	        registerMapper.addCorpMember(member);
	    }
	    return 1;
		
	}
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return registerMapper.isIdCheck(memberId);
	}



	
	

	
}
