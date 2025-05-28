package ks54team01.customer.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;
import ks54team01.customer.payment.domain.CustomerRefund;

@Mapper
public interface CustomerPaymentMapper {
	
	String getRentalContractNo(String paymentCompletedNo);
	
	void modifyCancelQuantity(String ProdNo, String entCeoNo, Integer cancelQuantity);
	
	CustomerPayment getProductByOrderId(String orderId);
	
	int getQuantityByOrderId(String orderId);
	
	int addRefund(CustomerRefund customerRefund);
	
	CustomerPayment getLastBillingPayment(@Param ("custId")String custId, @Param("rentalContractNo") String rentalContractNo);
	
	void modifyBillingKey(CustomerPayment customerPayment);
	
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
