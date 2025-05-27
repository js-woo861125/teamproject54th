package ks54team01.customer.login.mapper;


import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;

@Mapper
public interface LoginMapper {
	// 입점업체정보 조회
	EntMember getEntMemberInfoById(String memberId);
	
	// 기업고객정보 조회
	CustomerMember getCorpMemberInfoById(String memberId);
		
	// 개인고객정보 조회
	CustomerMember getCustomerMemberInfoById(String memberId);
	
	// 회원 조회(로그인, 탈퇴) 
	CommonMember getMemberInfoById(String memberId);
	 
		
}
