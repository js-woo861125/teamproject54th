package ks54team01.customer.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class EntMember{
	
	public EntMember(CommonMember common) {
        this.memberId = common.getMemberId();
        this.memberPw = common.getMemberPw();
        this.memberType = common.getMemberType();
    }
	
	// 공통등록정보
	private String memberId;
	private String memberPw;
	private String memberType;
	
	// 대표 (ent_ceo 테이블)
	private String entCeoNo;
	private String entBrno;
	private String entName;
	private String entCeoName;
	private String entCeoEmail;
	private String entCeoPhone;
	private String entCeoAddr;
	private String entCeoDaddr;
	private String entBank;
	private String entBankNum;

	// 직원 (ent_emp 테이블)
	private String entPosition;
	private String entEmpName;
	private String entEmpEmail;
	private String entEmpPhone;
}
