package ks54team01.enterprise.delivery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.mapper.EnterpriseDeliveryMapper;
import ks54team01.enterprise.delivery.service.EnterpriseDeliveryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterpriseDeliveryServiceImpl implements EnterpriseDeliveryService{

	private final EnterpriseDeliveryMapper enterpriseDeliveryMapper; 
	
	@Override
	public void modifyDelivery(EnterpriseDelivery enterpriseDelivery) {
		
		enterpriseDeliveryMapper.modifyDelivery(enterpriseDelivery);
	}
	
	
	
	@Override
	public EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo) {
		
		return enterpriseDeliveryMapper.getDeliveryInfoByCode(delInfoNo);
	}
	
	
	@Override
	public List<EnterpriseDelivery> getSearchDeliveryList(String searchKey, String searchValue) {

		switch (searchKey) {
			case "delCompany" 	-> searchKey = "delivery_company";
			case "recipientNm" 	-> searchKey = "recipient_nm";		
		}
		List<EnterpriseDelivery> enterpriseDeliveryList = enterpriseDeliveryMapper.getSearchDeliveryList(searchKey, searchValue);
		
		return enterpriseDeliveryList;
		
	}
	
	
	@Override
	public List<EnterpriseDelivery> getDeliveryList() {

		List<EnterpriseDelivery> enterpriseDeliveryList = enterpriseDeliveryMapper.getDeliveryList();
		
		return enterpriseDeliveryList;
		
	}
	
}
