package ks54team01.enterprise.account.service;

import ks54team01.customer.member.domain.EntMember;

public interface EnterpriseAccountService {
	// 입점업체 개인정보 수정
	boolean modifyEntInfo(EntMember modifyMember, String loginMemberType);
	
	// 입점업체 개인정보 조회
	EntMember getEntInfoById(String loginId);

	


}
