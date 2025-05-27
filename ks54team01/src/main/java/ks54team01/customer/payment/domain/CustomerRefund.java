package ks54team01.customer.payment.domain;

import lombok.Data;

@Data
public class CustomerRefund {

	private String refundRequestNo;
	private String custId;
	private String entCeoNo;
	private String entEmpId;
	private String paymentCompletedNo;
	private String refundReason;
	private String refundDate;
	private String requestStatus;
	private String reviewContent;
	private String reviewNotificationDate;
	private String paymentKey;
	private String orderId;
	
}
