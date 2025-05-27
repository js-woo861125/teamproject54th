package ks54team01.customer.contract.domain;

import lombok.Data;

@Data
public class CustomerContract {

	private String rentalContractNo;
	private String entCeoNo;
	private String entEmpId;
	private String sellProdNo;
	private String custId;
	private String rentalContractStatus;
	private Integer contractQuantity;
	private Integer contractPeriod;
	private String rentalStartDate;
	private String rentalEndDate;
	private String rentalCancelDate;
	private String contractDate;
}
