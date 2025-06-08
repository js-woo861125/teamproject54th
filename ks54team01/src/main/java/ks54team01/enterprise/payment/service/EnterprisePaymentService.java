package ks54team01.enterprise.payment.service;

import java.util.List;
import java.util.Map;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;
import ks54team01.system.util.PageInfo;

public interface EnterprisePaymentService {
	
	
	
	PageInfo<EnterprisePayment> getPaymentList(Map<String, Object> searchParamMap);
	
	
	List<EnterprisePaymentDetail> getPaymentDetailListByContractNoAndStatus(String rentalContractNo, String unpaidStatus);

	List<EnterprisePaymentDetail> getPaymentDetailListByContractNo(String rentalContractNo);
	
	
	
}
