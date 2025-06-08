package ks54team01.admin.delivery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import ks54team01.admin.delivery.mapper.AdminDeliveryMapper;
import ks54team01.admin.delivery.service.AdminDeliveryService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminDeliveryServiceImpl implements AdminDeliveryService{

	private final AdminDeliveryMapper adminDeliveryMapper;
	
	
	
	
	
	@Override
	public PageInfo<AdminDeliveryInfo> getDeliveryInfoList(Map<String, Object> searchParamMap) {
		// 전체 행 개수 조회
		int contentRowCount = adminDeliveryMapper.getDeliveryInfoCount(searchParamMap);
		
		List<AdminDeliveryInfo> transferBoardList = adminDeliveryMapper.getDeliveryInfoList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	
	
	
	@Override
	public List<AdminDelivery> getDeliveryListByCustId(String custId) {
		return adminDeliveryMapper.getDeliveryListByCustId(custId);
	}
	
	
	
	
	@Override
	public List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue) {

		switch (searchKey) {
			case "custId" 	-> searchKey = "dl.cust_id";
			case "custNm" 	-> searchKey = "c.cust_nm";
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
