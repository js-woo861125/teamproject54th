package ks54team01.enterprise.payment.service;

import java.util.List;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;

public interface EnterprisePaymentService {
	
	List<EnterprisePaymentDetail> getPaymentDetailListByContractNoAndStatus(String rentalContractNo, String unpaidStatus);

	List<EnterprisePaymentDetail> getPaymentDetailListByContractNo(String rentalContractNo);
	
	List<EnterprisePayment> getSearchPaymentList(String searchKey, String searchValue);

	List<EnterprisePayment> getPaymentList();
}
