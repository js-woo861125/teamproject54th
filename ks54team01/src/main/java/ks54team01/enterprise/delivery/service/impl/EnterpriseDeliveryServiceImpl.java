package ks54team01.enterprise.delivery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.domain.EnterpriseDeliveryInfo;
import ks54team01.enterprise.delivery.mapper.EnterpriseDeliveryMapper;
import ks54team01.enterprise.delivery.service.EnterpriseDeliveryService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterpriseDeliveryServiceImpl implements EnterpriseDeliveryService{

	private final EnterpriseDeliveryMapper enterpriseDeliveryMapper; 
	
	
	
	
	
	
	@Override
	public PageInfo<EnterpriseDeliveryInfo> getDeliveryList(Map<String, Object> searchParamMap) {
		
		// 전체 행 개수 조회
		int contentRowCount = enterpriseDeliveryMapper.getDeliveryCount(searchParamMap);
		
		List<EnterpriseDeliveryInfo> transferBoardList = enterpriseDeliveryMapper.getDeliveryList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	
	
	
	
	
	
	@Override
	public void modifyDelivery(EnterpriseDelivery enterpriseDelivery) {
		
		enterpriseDeliveryMapper.modifyDelivery(enterpriseDelivery);
	}
	
	
	
	@Override
	public EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo) {
		
		return enterpriseDeliveryMapper.getDeliveryInfoByCode(delInfoNo);
	}
	
	
}
