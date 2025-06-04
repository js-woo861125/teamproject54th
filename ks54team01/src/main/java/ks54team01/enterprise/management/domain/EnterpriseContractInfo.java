package ks54team01.enterprise.management.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EnterpriseContractInfo {
	
	public String entContractNo;
	public String entCeoNo;
	public String entEmpId;
	public String managerId;
	public Double feeRateSales;
	public Double feeRateRental;
	public Double feeRatePenalty;
	public Integer entryFee;
	public LocalDate contractDate;
	public LocalDate contractEndDate;
	public String entCalDate;
	public String contractStatus;
	
	public String entCeoName;
	public String entBrno;
	public String entName;
	public String entCeoAddr;
	public String entCeoDaddr;
	public String entCeoEmail;
	public String entCeoBank;
	public String entBankNum;
	public String entCeoPhone;
	public LocalDateTime entRegDate;
	
}

