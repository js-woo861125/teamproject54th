package ks54team01.enterprise.paymentCalc.service;

import java.util.List;

import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;

public interface PaymentServiceCalc {

	List<AdminFee> getEnterPricePayCalc(String ceoCode, String settlementMonth);
	
	List<AdminMonthlyFee> getEntMonthlyCalc(String ceoCode, String settlementMonth);
		
	
}
