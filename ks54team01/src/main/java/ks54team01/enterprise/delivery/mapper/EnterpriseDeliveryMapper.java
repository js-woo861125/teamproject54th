package ks54team01.enterprise.delivery.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.domain.EnterpriseDeliveryInfo;

@Mapper
public interface EnterpriseDeliveryMapper {
	
	
	int getDeliveryCount(Map<String, Object> searchParamMap);
	
	List<EnterpriseDeliveryInfo> getDeliveryList(Map<String, Object> searchParamMap);
	
	int modifyDelivery(EnterpriseDelivery enterpriseDelivery);
	
	EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo);
	
}
