package ks54team01.customer.payment.domain;

import lombok.Data;

@Data
public class CustomerPayment {
	
	private String paymentCompletedNo;
	private String custId;
	private String sellProdNo;
	private String rentalContractNo;
	private String paymentInfoNo;
	private String entCeoNo;
	private String entEmpNo;
	private String managerId;
	private String paymentType;
	private String contractDetail;
	private String paymentDetail;
	private Integer paymentCount;
	private Integer prodUnitPrice;
	private Integer totalPrice;
	private String paymentCompletedDate;
	private String paymentStatus;
	private String revDate;
	
	private String quantity;
	
	private String prodNm;
}
