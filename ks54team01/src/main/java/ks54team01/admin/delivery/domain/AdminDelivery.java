package ks54team01.admin.delivery.domain;

import lombok.Data;

@Data
public class AdminDelivery {

	private String delNo;
	private String custId;
	private String delNm;
	private String primaryLocation;
	private String delAddr;
	private String delDaddr;
	private String delZipNo;
	private String recipientPhone;
	private String delRequest;
	private String regDate;
	private String revDate;
	
	private AdminCustomer customerInfo;
}
