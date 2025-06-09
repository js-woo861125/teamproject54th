package ks54team01.enterprise.refund.service;

import java.io.IOException;
import java.util.Map;

import ks54team01.enterprise.refund.domain.EnterpriseRefund;
import ks54team01.system.util.PageInfo;

public interface EnterpriseRefundService {


	PageInfo<EnterpriseRefund> getRefundList(Map<String, Object> searchParamMap);
	
	void rejectRefund(String orderId, String paymentKey);
	
	void processRefund(String orderId, String paymentKey) throws IOException, InterruptedException;
	
}
