package ks54team01.admin.delivery.service;

import java.util.List;
import java.util.Map;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import ks54team01.system.util.PageInfo;

public interface AdminDeliveryService {
	
	
	PageInfo<AdminDeliveryInfo> getDeliveryInfoList(Map<String, Object> searchParamMap);
	
	
	List<AdminDelivery> getDeliveryListByCustId(String custId);
	

	List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue);
	
	
	List<AdminDelivery> getDeliveryList();
}
