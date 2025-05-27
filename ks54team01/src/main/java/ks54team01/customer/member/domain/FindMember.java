package ks54team01.customer.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMember {

	private String memberId;
	private String memberPw;
	private String memberType;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String withdrawStatus;
	
}

