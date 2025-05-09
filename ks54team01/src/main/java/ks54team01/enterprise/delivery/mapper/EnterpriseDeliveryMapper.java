package ks54team01.enterprise.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;

@Mapper
public interface EnterpriseDeliveryMapper {
	
	
	int modifyDelivery(EnterpriseDelivery enterpriseDelivery);
	
	EnterpriseDelivery getDeliveryInfoByCode(String delInfoNo);
	
	List<EnterpriseDelivery> getSearchDeliveryList(String searchKey, String searchValue);

	List<EnterpriseDelivery> getDeliveryList();
}
