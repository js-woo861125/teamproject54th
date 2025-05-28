package ks54team01.enterprise.refund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.refund.domain.EnterpriseRefund;

@Mapper
public interface EnterpriseRefundMapper {

	void modifyRefundApproved(String orderId, String requestStatus);
	
	List<EnterpriseRefund> getRefundList(String entCeoNo);
	
}
