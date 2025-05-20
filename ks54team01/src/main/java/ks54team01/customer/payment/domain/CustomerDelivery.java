package ks54team01.customer.payment.domain;

import lombok.Data;

@Data
public class CustomerDelivery {

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
}
