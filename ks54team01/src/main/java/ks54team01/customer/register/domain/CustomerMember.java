package ks54team01.customer.register.domain;

import lombok.Data;

@Data
public class CustomerMember {
	// 공통회원 (members 테이블)
	private String memberId;
	private String memberPw;
	private String memberType;
	private String memberDormantStatus;
	private String memberWithdrawStatus;
	private String memberStateTransitionDate;
	
	// 일반회원 (customer 테이블)
	private String custName; 
	private String custBrdt;
	private String custAddr;
	private String custDaddr;
	private String custEmail;
	private String custPhone;
	
	// 기업회원 (corp_customer 테이블) 
	private String corpBrno;
	private String corpName;
	
}

