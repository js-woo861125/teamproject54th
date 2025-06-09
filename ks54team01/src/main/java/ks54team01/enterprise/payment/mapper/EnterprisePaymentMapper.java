package ks54team01.enterprise.payment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;

@Mapper
public interface EnterprisePaymentMapper {
	
	int getPaymentCount(Map<String, Object> searchParamMap);
	
	List<EnterprisePayment> getPaymentList(Map<String, Object> searchParamMap);
	
	
	
	List<EnterprisePaymentDetail> getPaymentDetailListByContractNoAndStatus(@Param("rentalContractNo") String rentalContractNo, @Param("unpaidStatus") String unpaidStatus);

	List<EnterprisePaymentDetail> getPaymentDetailListByContractNo(@Param("rentalContractNo") String rentalContractNo);
	
}
