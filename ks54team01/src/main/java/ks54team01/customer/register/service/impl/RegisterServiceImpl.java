package ks54team01.customer.register.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		log.info("addEntEmpMember 시작");
		
		return registerMapper.addEntEmpMember(member);	
	}
	
	/**
	 * 입점업체대표정보 등록 
	 */
	@Override
	public int addEntCeoMember(EntMember member) {
	    log.info("addEntCeoMember 시작");

	    // 공통 필드 가져오기
	    CustomerMember customerMember = new CustomerMember();
	    customerMember.setMemberId(member.getMemberId());
	    customerMember.setMemberPw(member.getMemberPw());
	    customerMember.setMemberType(member.getMemberType());

	    try {
	        // members 테이블에 먼저 insert
	        registerMapper.addBasicMember(customerMember);

	        // 입점업체 대표 정보 등록 (ent_ceo, ent_emp 테이블)
	        registerMapper.addEntCeoMember(member);
	        
	        return 1;
	    } catch (Exception e) {
	        log.error("회원 등록 중 에러 발생", e);
	        
	        throw new RuntimeException("회원 등록 실패", e);
	    }
	}
	
	/**
	 * 입점업체대표코드 자동생성
	 */
	public String generateEntCeoNo() {
	    Integer lastNumber = registerMapper.getLastEntCeoNumber();
	    int nextNumber = (lastNumber != null) ? lastNumber + 1 : 1;
	    return "ent_ceo_" + nextNumber;
	}
	
	/**
	 * 모든회원정보 등록
	 */
	@Override
	public int addBasicMember(CustomerMember member) {
	    log.info("addBasicMember 시작");

	    // members 테이블에 먼저 insert
	    registerMapper.addBasicMember(member); 
	    
	    // 일반회원정보 customer 테이블에 insert
	    registerMapper.addMember(member);

	    // 기업회원인 경우 corp_customer 테이블에도 insert
	    if ("기업고객".equals(member.getMemberType())) {
	        registerMapper.addCorpMember(member);
	    }

	    return 1;
	}
	
	/**
	 * 일반회원정보 등록
	 */
	@Override
	public int addMember(CustomerMember member) {
		
		return registerMapper.addMember(member);		
	}
	
	/**
	 * 개인 및 기업회원 정보 등록
	 */
	@Override
	public int addCustomerMember(CustomerMember member) {
		
		return registerMapper.addCustomerMember(member);
		
	}

	/**
	 * 기업정보 등록
	 */
	@Override
	public int addCorpMember(CustomerMember member) {
		
		 return registerMapper.addCorpMember(member);
		
	}
	
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return registerMapper.isIdCheck(memberId);
	}



	
	

	
}
