package ks54team01.admin.contract.domain;

import lombok.Data;

@Data
public class AdminContract {

	private String rentContNo;
	private String entCeoNo;
	private String entEmpId;
	private String sellProdNo;
	private String custId;
	private String rentContStatus;
	private int contQuantity;
	private int contPeriod;
	private String rentStartDate;
	private String rentEndDate;
	private String rentCancelDate;
	private String contDate;
	
}
