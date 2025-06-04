package ks54team01.enterprise.common.service;

import java.util.List;

import ks54team01.admin.payment.domain.AdminMonthlyFee;

public interface CommonService {

	 List<AdminMonthlyFee> getEntMonthlyCalc(String entCeoNo, String settlementMonth);
	
	
	
}
