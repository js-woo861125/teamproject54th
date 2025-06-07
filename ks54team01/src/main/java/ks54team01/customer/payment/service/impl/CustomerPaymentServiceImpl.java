package ks54team01.customer.payment.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;
import ks54team01.customer.payment.domain.CustomerRefund;
import ks54team01.customer.payment.mapper.CustomerPaymentMapper;
import ks54team01.customer.payment.service.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

	private final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
	
	private final String SECRET_BILLING_KEY = "test_sk_Gv6LjeKD8a9q1PdGzbPN8wYxAdXy";
	
	private final ObjectMapper objectMapper;
	
	private final CustomerPaymentMapper customerPaymentMapper;
	
	
	
	
	
	@Override
	public CustomerPayment getFirstPaymentDetail(String rentalContractNo) {
		
		CustomerPayment customerPayment = customerPaymentMapper.getFirstPaymentDetail(rentalContractNo);
		
		return customerPayment;
	}
	
	
	@Override
	public List<CustomerPayment> getPaymentDetailList(String rentalContractNo) {
		
		List<CustomerPayment> customerPayment = customerPaymentMapper.getPaymentDetailList(rentalContractNo);
		
		return customerPayment;
	}
	
	
	
	@Override
	public String getRentalContractNo(String paymentCompletedNo) {

		String contractNo = customerPaymentMapper.getRentalContractNo(paymentCompletedNo);
		
		return contractNo;
	}
	
	
	@Override
	public void modifyCancelQuantity(CustomerPayment cancelProduct, Integer cancelQuantity) {

		String ProdNo = cancelProduct.getProdNo();
		String entCeoNo = cancelProduct.getEntCeoNo();
		
		customerPaymentMapper.modifyCancelQuantity(ProdNo, entCeoNo, cancelQuantity);
		
	}
	
	
	@Override
	public CustomerPayment getProductByOrderId(String orderId) {
		CustomerPayment customerPayment = customerPaymentMapper.getProductByOrderId(orderId);
		
		return customerPayment;
	}
	
	@Override
	public int getQuantityByOrderId(String orderId) {
		int quantity = customerPaymentMapper.getQuantityByOrderId(orderId);
		
		return quantity;
	}
	
	
	
	@Override
	public void addRefund(String orderId, String paymentCompletedNo, String paymentKey, String refundReason, String custId, String entCeoNo, String entEmpId) {
		
		String refundNo = "refund_" + UUID.randomUUID().toString().replace("-", "");
		
		CustomerRefund customerRefund = new CustomerRefund();
		
		customerRefund.setRefundRequestNo(refundNo);
		customerRefund.setCustId(custId);
		customerRefund.setPaymentCompletedNo(paymentCompletedNo);
		customerRefund.setPaymentKey(paymentKey);
		customerRefund.setRefundReason(refundReason);
		customerRefund.setOrderId(orderId);
		customerRefund.setEntCeoNo(entCeoNo);
		customerRefund.setEntEmpId(entEmpId);
		
		customerPaymentMapper.addRefund(customerRefund);
		
	}
	
	
	
	@Override
	public void modifyBillingKey(String custId, String billingKey, String rentalContractNo) {

		log.info("쿼리 호출 전: custId={}, rentalContractNo={}", custId, rentalContractNo);
		CustomerPayment lastPayment = customerPaymentMapper.getLastBillingPayment(custId, rentalContractNo);
		log.info("쿼리 결과: {}", lastPayment);
		
		
		
		 if (lastPayment == null) {
		        log.warn("최근 결제 내역을 찾을 수 없습니다. custId={}, rentalContractNo={}", custId, rentalContractNo);
		        throw new IllegalStateException("최근 결제 정보를 찾을 수 없습니다."); 
		    }
		
		
		lastPayment.setBillingKey(billingKey);
        customerPaymentMapper.modifyBillingKey(lastPayment);
	    
		
	}
	
	
	
	
	
	private boolean requestAutoBilling(CustomerPayment payment) {
	    try {
	        String newOrderId = "order_id_" + UUID.randomUUID().toString().replace("-", "");
	        String billingKey = payment.getBillingKey();
	        
	        String body = String.format("""
	            {
	                "customerKey": "%s",
	                "amount": %d,
	                "orderId": "%s",
	                "orderName": "정기결제 - %s"
	            }
	            """, payment.getCustomerKey(), payment.getTotalPrice(), newOrderId, payment.getSellProdNo());

	        String encodedKey = Base64.getEncoder().encodeToString((SECRET_BILLING_KEY + ":").getBytes());

	        HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://api.tosspayments.com/v1/billing/" + billingKey))
	            .header("Authorization", "Basic " + encodedKey)
	            .header("Content-Type", "application/json")
	            .POST(HttpRequest.BodyPublishers.ofString(body))
	            .build();

	        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

	        if (response.statusCode() == 200) {
	            String paymentKey = objectMapper.readTree(response.body()).get("paymentKey").asText();
	            payment.setPaymentKey(paymentKey); // 성공한 결제 키 저장
	            return true;
	        } else {
	            log.error("Toss 응답 오류: {}", response.body());
	            return false;
	        }

	    } catch (Exception e) {
	        log.error("자동결제 요청 예외: {}", e.getMessage(), e);
	        return false;
	    }
	}

	
	
	
	
	
	@Override
	public void autoBillingPayments() {

		 List<CustomerPayment> targets = customerPaymentMapper.getPaymentTargets();
		 
		 log.info("targets: {}", targets);
		 
		for (CustomerPayment payment : targets) {
			Integer nowPeriod = payment.getPaymentCountPeriod();
			Integer maxPeriod = payment.getContractPeriod();
			
			if (nowPeriod == null || maxPeriod == null || nowPeriod >= maxPeriod) {
				log.info("정기결제 종료 대상 제외: rentalContractNo={}, custId={}", payment.getRentalContractNo(), payment.getCustId());
				continue;
			}
			log.info("결제전: {}",payment );
			
			boolean success = requestAutoBilling(payment);
			if (success) {
				// 새 결제 데이터 준비
				payment.setPaymentCountPeriod(nowPeriod + 1); // 회차 +1
				payment.setPaymentCompletedNo("pay_" + UUID.randomUUID().toString().replace("-", ""));
				payment.setOrderId("order_id_" + UUID.randomUUID().toString().replace("-", ""));
				payment.setPaymentStatus("정상결제상태");
				payment.setNextPaymentDate(null);
				if(maxPeriod > nowPeriod + 1) {					
					LocalDate nextDate = LocalDate.now().plusMonths(1);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					payment.setNextPaymentDate(nextDate.format(formatter));
				}
				
				
				log.info("결제 후 : {}", payment);
				customerPaymentMapper.addNextScheduledPayment(payment);
				
			} else {
				log.warn("정기결제 실패: custId={}, rentalContractNo={}", payment.getCustId(), payment.getRentalContractNo());
			}
		}
		 
		 
		
	}
	
	
	
	@Override
	public void removeDeliveryInfo(String paymentCompletedNo) {

		customerPaymentMapper.removeDeliveryInfo(paymentCompletedNo);
		
	}
	
	
	@Override
	public void addDeliveryInfo(CustomerDeliveryInfo customerDeliveryInfo) {

		customerPaymentMapper.addDeliveryInfo(customerDeliveryInfo);
	}
	
	
	@Override
	public void cancelPayment(String paymentKey, String cancelReason) {

		try {
            String url = "https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel";
            String encodedKey = Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes());
            String body = String.format("{\"cancelReason\": \"%s\"}", cancelReason);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Basic " + encodedKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException(response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}
	
	
	@Override
	public String getPaymentKeyByOrderId(String orderId) {
		
		return customerPaymentMapper.getPaymentKeyByOrderId(orderId);
	}
	
	@Override
	public void modifyPaymentStatus(String orderId, String paymentStatus) {
		
		customerPaymentMapper.modifyPaymentStatus(orderId, paymentStatus);
		
	}
	
	
	@Override
	public void modifyQuantity(Integer orderQuantity, String prodNo, String entCeoNo) {

		customerPaymentMapper.modifyQuantity(orderQuantity, prodNo, entCeoNo);
	}
	
	
	@Override
	public int getQuantity(String prodNo, String entCeoNo) {
		
		int quantity = customerPaymentMapper.getQuantity(prodNo, entCeoNo);
		
		return quantity;
	}
	
	@Override
	public List<CustomerDelivery> getDeliveryListById(String custId) {
		
		List<CustomerDelivery> customerDelivery = customerPaymentMapper.getDeliveryListById(custId);
		
		return customerDelivery;
	}
	
	
	
	
	@Override
	public List<CustomerPayment> getPaymentList(String custId) {
		
		List<CustomerPayment> customerPayment = customerPaymentMapper.getPaymentList(custId);
		
		return customerPayment;
	}
	
	
	@Override
	public void addPayment(CustomerPayment customerPayment) {

		String paymentCompletedNo = "pay_" + UUID.randomUUID().toString().replace("-", "");
		customerPayment.setPaymentCompletedNo(paymentCompletedNo);
		
		customerPaymentMapper.addPayment(customerPayment);
	}
	
	
	
	@Override
	public void addBillingPayment(CustomerPayment customerPayment, String rentalContractNo) {
		
		String orderId = "order_id_" + UUID.randomUUID().toString().replace("-", "");
		customerPayment.setOrderId(orderId);
		
		String billingKey = customerPayment.getBillingKey();
		
		
		try {
			
			 // 결제 요청 생성
			String encodedKey = Base64.getEncoder().encodeToString((SECRET_BILLING_KEY + ":").getBytes());
			String requestBody = String.format("""
				{
				  "customerKey": "%s",
				  "amount": %d,
				  "orderId": "%s",
				  "orderName": "정기결제 - %s"
				}
				""",
			    customerPayment.getCustomerKey(),
			    customerPayment.getTotalPrice(),
			    orderId,
			    customerPayment.getSellProdNo()
			);
			
			HttpRequest request = HttpRequest.newBuilder()
								             .uri(URI.create("https://api.tosspayments.com/v1/billing/"+ billingKey))
								             .header("Authorization", "Basic " + encodedKey)
								             .header("Content-Type", "application/json")
								             .POST(HttpRequest.BodyPublishers.ofString(requestBody))
								             .build();
			
			
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			
			if (response.statusCode() == 200) {
			    JsonNode json = objectMapper.readTree(response.body());
			
			    // 결제 성공시 처리
				String paymentKey = json.get("paymentKey").asText();
				customerPayment.setPaymentKey(paymentKey);
				customerPayment.setPaymentStatus("정상결제상태");
			
				String paymentCompletedNo = "pay_" + UUID.randomUUID().toString().replace("-", "");
				customerPayment.setPaymentCompletedNo(paymentCompletedNo);
				customerPayment.setRentalContractNo(rentalContractNo);
				
				LocalDate nextDate = LocalDate.now().plusMonths(1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				customerPayment.setNextPaymentDate(nextDate.format(formatter));
			
			    customerPaymentMapper.addBlillingPayment(customerPayment);
			
			} else {
			    log.error("첫 결제 실패: {}", response.body());
			    throw new RuntimeException("Toss Billing 결제 실패");
			}
			
		} catch (Exception e) {
	        log.error("정기결제 중 예외 발생", e);
	        throw new RuntimeException("정기결제 중 오류", e);
	    }
		
	}
	
	
	
	@Transactional(timeout = 300, rollbackFor = Exception.class)
	@Override
	public Map<String, Object> confirmPaymemt(String paymentKey, String orderId, Long amount) {
		// TODO Auto-generated method stub
		String testSecretApiKey = SECRET_KEY + ":";
        String authKey = new String(Base64.getEncoder().encode(testSecretApiKey.getBytes(StandardCharsets.UTF_8)));

        Map<String, Object> responseMap = null;
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
                .header("Authorization", "Basic " + authKey)
                .header("Content-Type", "application/json")
                .method("POST"
                        , HttpRequest
                                .BodyPublishers
                                .ofString("{\"paymentKey\":\"" + paymentKey + "\",\"amount\":\"" + amount + "\",\"orderId\":\"" + orderId + "\"}")
                ).build();

        try {
			HttpResponse<String> response = HttpClient
			        .newHttpClient()
			        .send(request, HttpResponse.BodyHandlers.ofString());
			responseMap = objectMapper.readValue(response.body(), new TypeReference<>() {});
			log.info("결제 후 상세내용: {}", responseMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMap;
	}
	
	
	@Transactional(timeout = 300, rollbackFor = Exception.class)
	@Override
	public Map<String, Object> getBillingKey(String authKey, String customerKey) {
		
		String testSecretApiKey = SECRET_BILLING_KEY + ":";
		String encodeAuthKey = new String(Base64.getEncoder().encode(testSecretApiKey.getBytes(StandardCharsets.UTF_8)));
		
		Map<String, Object> responseMap = null;
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
			    .header("Authorization", "Basic " + encodeAuthKey)
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"authKey\":\"" + authKey + "\",\"customerKey\":\"" + customerKey + "\"}"))
			    .build();
		
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			responseMap = objectMapper.readValue(response.body(), new TypeReference<>() {});
			log.info("결제 후 상세내용: {}", responseMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMap;		
	}
	
}
