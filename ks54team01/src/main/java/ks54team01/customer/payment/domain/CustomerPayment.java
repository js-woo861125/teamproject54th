package ks54team01.customer.payment.domain;

import lombok.Data;

@Data
public class CustomerPayment {
	
	private String paymentCompletedNo;
	private String custId;
	private String sellProdNo;
	private String rentalContractNo;
	private String entCeoNo;
	private String entEmpId;
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
	private String paymentKey;
	private String orderId;
	private String customerKey;
	private String billingKey;
	private Integer paymentCountPeriod;
	private Integer contractPeriod;
	private String nextPaymentDate;
		
	private String prodNm;
}
