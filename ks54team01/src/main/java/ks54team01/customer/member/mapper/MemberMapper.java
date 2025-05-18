package ks54team01.customer.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CustomerMember;

@Mapper
public interface MemberMapper {
	// 기업고객 정보 조회
	CustomerMember getCorpInfoById(String memberId);
	
	// 개인 및 기업고객 정보 조회
	CustomerMember getCustomerInfoById(String memberId);


}
