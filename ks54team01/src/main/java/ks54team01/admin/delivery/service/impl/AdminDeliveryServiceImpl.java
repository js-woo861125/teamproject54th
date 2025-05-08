package ks54team01.admin.delivery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import ks54team01.admin.delivery.mapper.AdminDeliveryMapper;
import ks54team01.admin.delivery.service.AdminDeliveryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminDeliveryServiceImpl implements AdminDeliveryService{

	private final AdminDeliveryMapper adminDeliveryMapper;
	
	
	
	@Override
	public List<AdminDeliveryInfo> getSearchDeliveryInfoList(String searchKey, String searchValue) {

		switch (searchKey) {
		case "delCompany" 	-> searchKey = "delivery_company";
		case "delProgress" 	-> searchKey = "delivery_progress";
		case "recipientNm" 	-> searchKey = "recipient_nm";		
		}
		List<AdminDeliveryInfo> adminDeliveryInfoList = adminDeliveryMapper.getSearchDeliveryInfoList(searchKey, searchValue);
		
		return adminDeliveryInfoList;
		
	}
	
	@Override
	public List<AdminDeliveryInfo> getDeliveryInfoList() {

		List<AdminDeliveryInfo> adminDeliveryInfoList = adminDeliveryMapper.getDeliveryInfoList();
		
		return adminDeliveryInfoList;
	}
	
	@Override
	public List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue) {

		switch (searchKey) {
			case "custId" 	-> searchKey = "dl.cust_id";
			case "custNm" 	-> searchKey = "c.cust_nm";
			case "primaryLocation" 	-> searchKey = "dl.primary_location";		
		}
		List<AdminDelivery> adminDeliveryList = adminDeliveryMapper.getSearchDeliveryList(searchKey, searchValue);
		
		return adminDeliveryList;
	}
	
	
	
	@Override
	public List<AdminDelivery> getDeliveryList() {
		
		List<AdminDelivery> adminDeliveryList = adminDeliveryMapper.getDeliveryList();
		
	 	return adminDeliveryList;
	}
	
	
}
