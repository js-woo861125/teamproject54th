package ks54team01.customer.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerMember {
	
	public CustomerMember(CommonMember common) {
		this.memberId = common.getMemberId();
		this.memberPw = common.getMemberPw();
	}
	
	// 공통정보 (members 테이블)
	private String memberId;
    private String memberPw;
    private String memberType;
    
	// 개인회원 (customer 테이블)
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

