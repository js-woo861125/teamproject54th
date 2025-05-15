package ks54team01.enterprise.payment.domain;

import lombok.Data;

@Data
public class EnterprisePaymentDetail {

	private String rentalContractDetailNo;
	private String rentalContractNo;
	private String entCeoNo;
	private String custId;
	private Integer paidInRounds;
	private Integer payAmount;
	private Integer penaltyFee;
	private String monthlyPayDate;
	private String payStatus;
	private String unpaidStatus;
}
