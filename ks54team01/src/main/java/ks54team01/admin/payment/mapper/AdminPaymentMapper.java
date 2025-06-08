package ks54team01.admin.payment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminPayment;

@Mapper
public interface AdminPaymentMapper {
	
	
	int getPaymentCount(Map<String, Object> searchParamMap);
	
	
	List<AdminPayment> getPaymentPageList(Map<String, Object> searchParamMap);
	
	// 입점업체 검색
	List<AdminEntList> getSearchEnt(String searchKey, String searchValue);
	
	// 입접 업체별 수수료 조회
	List<AdminFee> getSearchEntFee(String entCeoNo, String settlementMonth);
	

}
