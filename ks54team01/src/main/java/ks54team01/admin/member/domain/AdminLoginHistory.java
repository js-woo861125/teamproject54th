package ks54team01.admin.member.domain;

import lombok.Data;

@Data
public class AdminLoginHistory {
	
	private String loginNo;
	private String memberId;
	private String loginDate;
	private String logoutDate;
	
	private AdminMember memberInfo;
}
