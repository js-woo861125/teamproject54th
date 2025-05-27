package ks54team01.enterprise.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;

@Mapper
public interface EnterprisePaymentMapper {
	
	List<EnterprisePaymentDetail> getPaymentDetailListByContractNoAndStatus(@Param("rentalContractNo") String rentalContractNo, @Param("unpaidStatus") String unpaidStatus);

	List<EnterprisePaymentDetail> getPaymentDetailListByContractNo(@Param("rentalContractNo") String rentalContractNo);
	
	List<EnterprisePayment> getSearchPaymentList(String searchKey, String searchValue);

	List<EnterprisePayment> getPaymentList();
}
