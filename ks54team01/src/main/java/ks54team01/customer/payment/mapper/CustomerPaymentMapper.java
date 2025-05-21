package ks54team01.customer.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerPayment;

@Mapper
public interface CustomerPaymentMapper {
	
	void modifyQuantity( Integer orderQuantity, String prodNo, String entCeoNo);
	
	int getQuantity(String prodNo, String entCeoNo);
	
	List<CustomerDelivery> getDeliveryListById(String custId);
	
	List<CustomerPayment> getPaymentList(String custId);

	int addPayment(CustomerPayment customerPayment);
}
