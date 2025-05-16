package ks54team01.customer.delivery.service;

import java.util.List;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;

public interface CustomerDeliveryService {
	
	void addDeliveryList(CustomerDeliveryList customerDeliveryList);

	List<CustomerDeliveryList> getDeliveryList();
}
