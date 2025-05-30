package ks54team01.enterprise.refund.service;

import java.io.IOException;
import java.util.List;

import ks54team01.enterprise.refund.domain.EnterpriseRefund;

public interface EnterpriseRefundService {

	
	void rejectRefund(String orderId, String paymentKey);
	
	void processRefund(String orderId, String paymentKey) throws IOException, InterruptedException;
	
	List<EnterpriseRefund> getRefundList(String entCeoNo);
}
