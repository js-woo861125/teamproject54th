package ks54team01.admin.payment.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminMonthlyFee {

	private String monthlyFeeNo;
	private String entCeoNo;
	private String entEmpId;
	private String managerId;
	private String paymentDetails;
	private int platformFee;
	private int entFee;
	private String payStatus;
	private LocalDate provisionDate;
	private String settlementMonth;



}
