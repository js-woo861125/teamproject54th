package ks54team01.customer.member.service;

import org.springframework.stereotype.Service;

import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.FindMember;

@Service
public interface MemberService {
	// 임시비밀번호 업데이트
	void updateRandomPw(String memberId, String tempPw);
	
	// 비밀번호 찾기
	FindMember findMemberPwByInfo(String memberId, String memberEmail, String memberType);
	
	// 아이디 찾기
	FindMember findMemberIdByInfo(String memberName, String memberPhone, String memberEmail, String memberType);
		
	// 회원탈퇴
	boolean customerLeave(String memberId, String memberId2);
	
	// 처리 진행중인 상태 여부 조회(탈퇴)
	boolean checkStatus(String memberId);
	
	// 회원정보 수정
	boolean modifyCustomerInfo(CustomerMember modifyMember, String newPw);
	
	// 기업고객 정보 조회
	CustomerMember getCorpInfoById(String loginId);
	
	// 개인 및 기업고객 정보 조회
	CustomerMember getCustomerInfoById(String loginId);

	// 비밀번호 일치여부 체크
	boolean isPwCheck(String memberId, String memberPw);





}
