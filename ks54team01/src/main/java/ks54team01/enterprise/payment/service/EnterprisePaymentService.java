package ks54team01.enterprise.payment.service;

import java.util.List;

import ks54team01.enterprise.payment.domain.EnterprisePayment;

public interface EnterprisePaymentService {
	
	List<EnterprisePayment> getSearchPaymentList(String searchKey, String searchValue);

	List<EnterprisePayment> getPaymentList();
}
