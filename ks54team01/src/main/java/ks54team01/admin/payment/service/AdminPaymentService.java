package ks54team01.admin.payment.service;

import java.util.List;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminPayment;

public interface AdminPaymentService {

	
	List<AdminEntList> getSearchEnt(String searchKey, String searchValue);
	
	List<AdminFee> getAdminPayFee(String ceoCode);
	
	List<AdminPayment> getSearchPaymentList(String searchKey, String searchValue);
	
	List<AdminPayment> getPaymentList();
}
