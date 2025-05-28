package ks54team01.admin.member.domain;

import lombok.Data;

@Data
public class AdminMemberDetail {

	// 공통
	private String memberId;
	private String memberPw;
	private String memberType;
	private String memberName;
	private String dormantStatus;
	private String withdrawStatus;
	private String memberRegDate;
	private String memberRevDate;
	private String stateTransDate;
	
	// 개인회원
	private String custPhone;
	private String custBrdt;
	private String custAddr;
	private String custDaddr;
	private String custEmail;
	
	// 기업회원
	private String corpName;
	private String corpBrno;
	
	// 플랫폼직원
	private String managerName;
	private String managerPhone;
	private String managerBrdt;
	private String managerGender;
	
	// 입점업체
	private String entPosition;
	private String entEmpName;
	private String entEmpEmail;
	private String entEmpPhone;
	private String entBrno;
	private String entName;
	private String entCeoAddr;
	private String entCeoDaddr;
	private String entBank;
	private String entBankNum;
	
	
}
