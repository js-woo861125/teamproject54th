package ks54team01.customer.payment.service;

import java.util.List;
import java.util.Map;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;

public interface CustomerPaymentService {
	
	
	CustomerPayment getFirstPaymentDetail(String rentalContractNo);
	
	List<CustomerPayment> getPaymentDetailList(String rentalContractNo);
	
	
	String getRentalContractNo(String paymentCompletedNo);
	
	void modifyCancelQuantity(CustomerPayment cancelProduct, Integer cancelQuantity);
	
	CustomerPayment getProductByOrderId(String orderId);
	
	int getQuantityByOrderId(String orderId);
	
	void addRefund(String orderId, String paymentCompletedNo, String paymentKey, String refundReason, String custId, String entCeoNo, String entEmpId);
	
	void modifyBillingKey(String custId, String billingKey, String rentalContractNo);
	
	void autoBillingPayments();
	
	void removeDeliveryInfo(String paymentCompletedNo);
	
	void addDeliveryInfo(CustomerDeliveryInfo customerDeliveryInfo);
	
	void cancelPayment(String paymentKey, String cancelReason);
	
	String getPaymentKeyByOrderId(String orderId);

    void modifyPaymentStatus(String orderId, String paymentStatus);
	
	void modifyQuantity(Integer orderQuantity, String prodNo, String entCeoNo);
	
	int getQuantity(String prodNo, String entCeoNo);
	
	List<CustomerDelivery> getDeliveryListById(String custId);
	
	List<CustomerPayment> getPaymentList(String custId);
	
	void addPayment(CustomerPayment customerPayment);
	
	void addBillingPayment(CustomerPayment customerPayment, String rentalContractNo);

	Map<String, Object> confirmPaymemt(String paymentKey, String orderId, Long amount);
	
	Map<String, Object> getBillingKey(String authKey, String customerKey);
	
}
