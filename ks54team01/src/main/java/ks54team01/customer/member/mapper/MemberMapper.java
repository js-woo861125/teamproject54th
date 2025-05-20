package ks54team01.customer.member.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CustomerMember;

@Mapper
public interface MemberMapper {
	// 기업회원 추가정보 수정
	int modifyCorpInfo(CustomerMember modifyMember);
	
	// 회원정보 수정
	int modifyCustomerInfo(CustomerMember modifyMember);
	
	// 공통정보 수정
	int modifyCommonInfo(Map<String, Object> commonInfoMap);
	
	// 비밀번호 일치여부 체크
	public boolean isPwCheck(Map<String, Object> params);
	
	// 기업고객 정보 조회
	CustomerMember getCorpInfoById(String memberId);
	
	// 개인 및 기업고객 정보 조회
	CustomerMember getCustomerInfoById(String memberId);


	




}
