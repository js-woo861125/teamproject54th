package ks54team01.customer.register.domain;

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
	private String entCeoId;
	private String entCeoNo;
	private String entCeoName;
	private String entBrno;
	private String entName;
	private String entCeoAddr;
	private String entCeoDaddr;
	private String entCeoEmail;
	private String entBank;
	private String entBankNum;
	private String entCeoPhone;
	
	// 직원 (ent_emp 테이블)
	private String entEmpId;
	private String entPosition;
	private String entEmpName;
	private String entEmpEmail;
	private String entEmpPhone;
}
