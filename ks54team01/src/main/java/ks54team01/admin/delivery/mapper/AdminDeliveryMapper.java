package ks54team01.admin.delivery.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;

@Mapper
public interface AdminDeliveryMapper {

	
	
	int getDeliveryInfoCount(Map<String, Object> searchParamMap);
	
	List<AdminDeliveryInfo> getDeliveryInfoList(Map<String, Object> searchParamMap);
	
	List<AdminDelivery> getDeliveryListByCustId(String custId);
	
	
	
	// 회원별 검색 배송지 조회
	List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue);
	
	
	// 전체 배송지 목록 조회
	List<AdminDelivery> getDeliveryList();
}
