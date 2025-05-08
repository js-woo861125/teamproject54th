package ks54team01.customer.member.domain;

import lombok.Data;

@Data
public class CustomerMember {

	private String memberId;
	private String memberPw;
	private String memberType;
	private String memberName;
	private String memberAddress;
	private String memberDetailAddress;
	private int memberZip;
	private String memberTelNo;
	private String memberEmail;
	private String memberRegDate;
		
}

