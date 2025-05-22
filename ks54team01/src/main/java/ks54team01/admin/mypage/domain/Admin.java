package ks54team01.admin.mypage.domain;

import ks54team01.admin.member.domain.AdminMember;
import lombok.Data;

@Data
public class Admin {
	
	private String managerId;
	private String managerName;
	private String managerGender;
	private String managerBrdt;
	private String managerPhone;
	private String managerRegisterDate;
	private String managerRevisionDate;
	
	private AdminMember adminMember;
	
}
