package ks54team01.customer.delivery.service;

import java.util.List;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;

public interface CustomerDeliveryService {
	
	boolean removeDeliveryList(String delNo);
	
	void modifyDeliveryList(CustomerDeliveryList modifyDeliveryList);
	
	void addDeliveryList(CustomerDeliveryList customerDeliveryList);

	List<CustomerDeliveryList> getDeliveryList(String custId);
}
