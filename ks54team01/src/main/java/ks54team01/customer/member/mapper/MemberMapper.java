package ks54team01.customer.member.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.FindMember;

@Mapper
public interface MemberMapper {
	// 임시비밀번호 업데이트
	void updatePassword(String memberId, String encodedPw);
	
	// 비밀번호 찾기
	FindMember findMemberPwByInfo(String memberId, String memberEmail, String memberType);
	
	// 아이디 찾기
	FindMember findMemberIdByInfo(String memberName, String memberPhone, String memberEmail, String memberType);
	
	// 공통정보 테이블 탈퇴처리
	int deactivateCommonMember(String memberId);
	
	// 기업고객 탈퇴
	int deactivateCorpMember(String memberId);
	
	// 개인고객 탈퇴
	int deactivateCustomerMember(String memberId);
	
	// 처리 진행중인 상태 여부 조회(탈퇴)
	int resultCountById(String memberId);
	
	// 기업회원 추가정보 수정
	int modifyCorpInfo(CustomerMember modifyMember);
	
	// 회원정보 수정
	int modifyCustomerInfo(CustomerMember modifyMember);
	
	// 공통정보 수정
	int modifyCommonInfo(Map<String, Object> commonInfoMap);
	
	// 기업고객 정보 조회
	CustomerMember getCorpInfoById(String memberId);
	
	// 개인 및 기업고객 정보 조회
	CustomerMember getCustomerInfoById(String memberId);






}
