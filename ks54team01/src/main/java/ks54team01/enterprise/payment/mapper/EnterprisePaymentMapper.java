package ks54team01.enterprise.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.payment.domain.EnterprisePayment;

@Mapper
public interface EnterprisePaymentMapper {
	
	List<EnterprisePayment> getSearchPaymentList(String searchKey, String searchValue);

	List<EnterprisePayment> getPaymentList();
}
