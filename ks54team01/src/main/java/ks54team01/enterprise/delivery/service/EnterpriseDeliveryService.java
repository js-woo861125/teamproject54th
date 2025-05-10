package ks54team01.enterprise.delivery.service;

import java.util.List;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;

public interface EnterpriseDeliveryService {
	
	
	void modifyDelivery(EnterpriseDelivery enterpriseDelivery);
	
	EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo);
	
	
	List<EnterpriseDelivery> getSearchDeliveryList(String searchKey, String searchValue);
	
	List<EnterpriseDelivery> getDeliveryList();

}
