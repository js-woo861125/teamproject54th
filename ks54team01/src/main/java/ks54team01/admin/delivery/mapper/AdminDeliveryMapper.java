package ks54team01.admin.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;

@Mapper
public interface AdminDeliveryMapper {

	// 상품배송정보 검색
	List<AdminDeliveryInfo> getSearchDeliveryInfoList(String searchKey, String searchValue);
	
	// 상품배송정보 조회
	List<AdminDeliveryInfo> getDeliveryInfoList();
	
	
	// 회원별 검색 배송지 조회
	List<AdminDelivery> getSearchDeliveryList(String searchKey, String searchValue);
	
	
	// 전체 배송지 목록 조회
	List<AdminDelivery> getDeliveryList();
}
