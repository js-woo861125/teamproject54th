package ks54team01.customer.member.domain;

import lombok.Data;

@Data
public class CustomerMember {

	private String memberId;
	private String memberPw;
	private String memberType;
	private String registerDate;
	private String revisionDate;
	private String dormantStatus;
	private String withdrawStatus;
	private String stateTransitionDate;
	
	private String custId;
	private String custName; 
	private String custBrdt;
	private String custAddr;
	private String custDaddr;
	private String custEmail;
	private String custPhone;
	
	private String corpBrno;
	private String corpName;
	
}

