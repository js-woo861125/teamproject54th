package ks54team01.admin.enterprise.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminEntDetail {
	
	private String entContractNo;
	private String CeoNo;
	private String CeoId;	
	private String CeoEmail;	
	private String entAddr;	
	private String entDaddr;	
	private Double feeRateSales;
	private Double feeRateRental;
	private Double feeRetePenalty;
	private Integer entryFee;
	private LocalDate  contractDate;
	private LocalDate contractEndDate;
	private String entCalDate;
	private String contractStatus;
}


