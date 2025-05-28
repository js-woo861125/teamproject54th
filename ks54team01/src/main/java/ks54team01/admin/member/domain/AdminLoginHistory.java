package ks54team01.admin.member.domain;

import lombok.Data;

@Data
public class AdminLoginHistory {

	private String loginNo;
	private String memberId;
	private String memberType;
	private String memberRegDate;
	private String dormantStatus;
	private String withdrawStatus;
	private String stateTransitionDate;
	private String loginDate;
	private String logoutDate;
	
}
