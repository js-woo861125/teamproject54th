package ks54team01.admin.enterprise.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminEntDetail {
	
	private String entContractNo;
	private String entCeoNo;
	private String entCeoId;	
	private String entCeoEmail;	
	private String entAddr;	
	private String entDaddr;	
	private Double feeRateSales;
	private Double teeRateRental;
	private Double feeRetePenalty;
	private Integer entryFee;
	private LocalDate  contractDate;
	private LocalDate contractEndDate;
	private String entCalDate;
	private String contractStatus;
}


