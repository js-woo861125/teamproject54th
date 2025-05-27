package ks54team01.admin.contract.domain;

import lombok.Data;

@Data
public class AdminContractDetail {

	// 0 계약번호상세
	private String rentalContractDetailNum;
	// 1 납입회차
	private int paidInRound;
	// 2 월납입금액
	private String paymentAmount;
	// 3 위약금
	private String penaltyFee;
	// 4 월별납입
	private String monthlyDate;
	// 5 납입예정일
	private String monthlyPaymentDate;
	// 6 납입여부
	private String unpaidStatus;
	// 7 계약유형
	private String rentalContType;
	// 0 계약번호
	private String rentalContNo;
	
	
	


	
}
