package ks54team01.admin.payment.service;

import java.util.List;

import ks54team01.admin.payment.domain.AdminPayment;

public interface AdminPaymentService {

	List<AdminPayment> getSearchPaymentList(String searchKey, String searchValue);
	
	
	List<AdminPayment> getPaymentList();
}
