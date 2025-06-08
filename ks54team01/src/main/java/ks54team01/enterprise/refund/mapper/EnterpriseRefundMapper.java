package ks54team01.enterprise.refund.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.refund.domain.EnterpriseRefund;

@Mapper
public interface EnterpriseRefundMapper {
	
	
	int getRefundCount(Map<String, Object> searchParamMap);
	
	List<EnterpriseRefund> getRefundList(Map<String, Object> searchParamMap);

	void modifyRefundApproved(String orderId, String requestStatus);
	
	
}
