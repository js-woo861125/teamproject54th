package ks54team01.admin.payment.service;

import ks54team01.admin.payment.domain.PaymentProcessRequest;
import ks54team01.admin.payment.domain.SettlementConfirmRequest;

public interface AdminFeeService {
	
	/**
     * 정산 확정 데이터를 저장
     * 
     */
	
	 void saveSettlement(SettlementConfirmRequest request);
	 
	 /**
	     * 지급 처리 로직
	     * fee 테이블의 provision_date를 현재 날짜로, payment_status를 '지급'으로 업데이트
	     * monthly_fee 테이블의 pay_status를 '지급'으로, provision_date를 현재 날짜로 업데이트
	     */
	    void processPayment(PaymentProcessRequest request);
	
}
