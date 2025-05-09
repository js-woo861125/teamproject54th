package ks54team01.admin.member.domain;

import lombok.Data;

@Data
public class AdminMember {

	private String memberId;
	private String memberPw;
	private String memberType;
	private String dormantStatus;
	private String withdrawStatus;
	private String memberRegDate;
	private String memberRevDate;
	private String stateTransDate;
}
