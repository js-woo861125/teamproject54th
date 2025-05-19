package ks54team01.customer.payment.service;

import java.util.List;
import java.util.Map;

import ks54team01.customer.payment.domain.CustomerPayment;

public interface CustomerPaymentService {
	
	List<CustomerPayment> getPaymentList(String custId);
	
	void addPayment(CustomerPayment customerPayment);

	Map<String, Object> confirmPaymemt(String paymentKey, String orderId, Long amount);
}
