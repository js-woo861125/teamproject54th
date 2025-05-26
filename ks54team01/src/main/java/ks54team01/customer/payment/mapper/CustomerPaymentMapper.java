package ks54team01.customer.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;

@Mapper
public interface CustomerPaymentMapper {
	
	
	CustomerPayment getLastBillingPayment(String custId);
	
	int modifyBillingKey(CustomerPayment customerPayment);
	
	int addNextScheduledPayment(CustomerPayment payment);
	
	// 정기결제할 사람 조회
	List<CustomerPayment> getPaymentTargets();

	void removeDeliveryInfo(String paymentCompletedNo);
	
	int addDeliveryInfo(CustomerDeliveryInfo customerDeliveryInfo);
	
	String getPaymentKeyByOrderId(String orderId);

    void modifyPaymentStatus(String orderId,String paymentStatus);
	
	void modifyQuantity( Integer orderQuantity, String prodNo, String entCeoNo);
	
	int getQuantity(String prodNo, String entCeoNo);
	
	List<CustomerDelivery> getDeliveryListById(String custId);
	
	List<CustomerPayment> getPaymentList(String custId);

	int addPayment(CustomerPayment customerPayment);
	
	int addBlillingPayment(CustomerPayment customerPayment);
}
