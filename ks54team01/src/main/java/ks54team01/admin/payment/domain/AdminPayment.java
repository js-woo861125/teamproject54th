package ks54team01.admin.payment.domain;

import ks54team01.admin.delivery.domain.AdminCustomer;
import lombok.Data;

@Data
public class AdminPayment {

	private String payCompNo;
	private String custId;
	private String sellProdNo;
	private String rentalContractNo;
	private String payInfoNo;
	private String entCeoNo;
	private String entEmpId;
	private String managerId;
	private String paymentType;
	private String contractDetails;
	private String payDetails;
	private int payCount;
	private int prodUnitPrice;
	private int totalPrice;
	private String payCompDate;
	private String payStatus;
	private String revDate;
	
	private AdminCustomer customerInfo;
	private AdminEntCeo entCeoInfo;
}
