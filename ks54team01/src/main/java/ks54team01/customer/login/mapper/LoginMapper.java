package ks54team01.customer.login.mapper;

import org.apache.ibatis.annotations.Mapper;

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
	
	// 고객공통정보 조회 
	String getMemberTypeById(String memberId);
	 
		
}
