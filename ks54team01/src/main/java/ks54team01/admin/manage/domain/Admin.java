package ks54team01.admin.manage.domain;

import ks54team01.admin.member.domain.AdminMember;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Admin {
	
	public Admin(AdminMember adminMember) {
		this.memberId = adminMember.getMemberId();
		this.memberPw = adminMember.getMemberPw();
		this.memberType = adminMember.getMemberType();
}
	private String memberId;
	private String memberPw;
	private String memberType;
	private String managerName;
	private String managerGender;
	private String managerBrdt;
	private String managerPhone;
	
	
}
