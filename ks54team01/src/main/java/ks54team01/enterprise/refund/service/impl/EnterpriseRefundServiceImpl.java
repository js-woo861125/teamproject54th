package ks54team01.enterprise.refund.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.payment.service.CustomerPaymentService;
import ks54team01.enterprise.refund.domain.EnterpriseRefund;
import ks54team01.enterprise.refund.mapper.EnterpriseRefundMapper;
import ks54team01.enterprise.refund.service.EnterpriseRefundService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EnterpriseRefundServiceImpl implements EnterpriseRefundService{

	private final EnterpriseRefundMapper enterpriseRefundMapper;
	
	private final CustomerPaymentService customerPaymentService;
	
	private void cancelPayment(String paymentKey, String reason) throws IOException, InterruptedException {
		String secretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
		String encodedKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel"))
			.header("Authorization", "Basic " + encodedKey)
			.header("Content-Type", "application/json")
			.POST(HttpRequest.BodyPublishers.ofString("""
				{
					"cancelReason": "%s"
				}
			""".formatted(reason)))
			.build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200) {
			throw new RuntimeException("Toss 환불 실패: " + response.body());
		}
	}
	
	
	
	@Override
	public PageInfo<EnterpriseRefund> getRefundList(Map<String, Object> searchParamMap) {

		// 전체 행 개수 조회
		int contentRowCount = enterpriseRefundMapper.getRefundCount(searchParamMap);
		
		List<EnterpriseRefund> transferBoardList = enterpriseRefundMapper.getRefundList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	
	
	
	@Override
	public void rejectRefund(String orderId, String paymentKey) {

		enterpriseRefundMapper.modifyRefundApproved(orderId, "환불거부");
		
		customerPaymentService.modifyPaymentStatus(orderId, "정상결제상태");
	}
	
	
	
	
	@Override
	public void processRefund(String orderId, String paymentKey) throws IOException, InterruptedException {
		
		cancelPayment(paymentKey, "입점업체 환불 승인");
		
		
		enterpriseRefundMapper.modifyRefundApproved(orderId, "환불승인");
		
		customerPaymentService.modifyPaymentStatus(orderId, "환불");
		
	}
	
	

	
	
	
	
}
