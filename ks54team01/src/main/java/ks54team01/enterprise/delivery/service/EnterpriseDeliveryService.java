package ks54team01.enterprise.delivery.service;

import java.util.Map;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.domain.EnterpriseDeliveryInfo;
import ks54team01.system.util.PageInfo;

public interface EnterpriseDeliveryService {
	
	
	PageInfo<EnterpriseDeliveryInfo> getDeliveryList(Map<String, Object> searchParamMap);
	
	void modifyDelivery(EnterpriseDelivery enterpriseDelivery);
	
	EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo);
	



}
