package ks54team01.customer.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;

@Mapper
public interface CustomerDeliveryMapper {

	int updatePrimaryLocation(String custId);
	
	
	int addDeliveryList(CustomerDeliveryList customerDeliveryList);
	
	List<CustomerDeliveryList> getDeliveryList();
	
}
