package ks54team01.enterprise.payment.domain;

import ks54team01.admin.delivery.domain.AdminCustomer;
import ks54team01.admin.payment.domain.AdminEntCeo;
import lombok.Data;

@Data
public class EnterprisePayment {

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
	private Integer payCount;
	private Integer prodUnitPrice;
	private Integer totalPrice;
	private String payCompDate;
	private String payStatus;
	private String revDate;
	
	private AdminCustomer customerInfo;
}
