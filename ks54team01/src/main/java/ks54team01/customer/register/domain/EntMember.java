package ks54team01.customer.register.domain;

import lombok.Data;

@Data
public class EntMember {
	// ent_ceo
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
	private String entCeoregisterDate;
	private String entCeorevisionDate;

	// ent_emp
	private String entEmpId;
	private String entPosition;
	private String entEmpName;
	private String entEmpEmail;
	private String entEmpPhone;
	private String entEmpregisterDate;
	private String entEmprevisionDate;
	
}
