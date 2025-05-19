package ks54team01.customer.payment.service;

import java.util.Map;

public interface CustomerPaymentService {

	Map<String, Object> confirmPaymemt(String paymentKey, String orderId, Long amount);
}
