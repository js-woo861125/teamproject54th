package ks54team01.admin.enterprise.domain;

import lombok.Data;

@Data
public class AdminEntAddContract {

	public String entContractNo;
	public String entCeoNo;
	public String entEmpId;
	public String managerId;
	public Double feeRateSales;
	public Double feeRateRental;
	public Double feeRatePenalty;
	public Integer entryFee;
	public String contractDate;
	public String contractEndDate;
	public String entCalDate;
	public String contractStatus;
	
}
