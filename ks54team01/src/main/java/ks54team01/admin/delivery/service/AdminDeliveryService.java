package ks54team01.admin.delivery.service;

import java.util.List;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;

public interface AdminDeliveryService {
	
	List<AdminDeliveryInfo> getSearchDeliveryInfoList(String searchKey, String searchValue);
	
	List<AdminDeliveryInfo> getDeliveryInfoList();

	List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue);
	
	
	List<AdminDelivery> getDeliveryList();
}
