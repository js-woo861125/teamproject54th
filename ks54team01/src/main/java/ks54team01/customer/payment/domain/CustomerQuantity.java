package ks54team01.customer.payment.domain;

import lombok.Data;

@Data
public class CustomerQuantity {

	private String quantityNo;
	private String prodNo;
	private String entCeoNo;
	private String entEmpId;
	private Integer quantity;
	private String regDate;
	private String revDate;
}
