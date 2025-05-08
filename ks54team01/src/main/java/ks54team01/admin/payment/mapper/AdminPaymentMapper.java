package ks54team01.admin.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.payment.domain.AdminPayment;

@Mapper
public interface AdminPaymentMapper {
	
	// 회원별 결제내역 검색
	List<AdminPayment> getSearchPaymentList(String searchKey, String searchValue);
	
	// 결제내역 조회
	List<AdminPayment> getPaymentList();

}
