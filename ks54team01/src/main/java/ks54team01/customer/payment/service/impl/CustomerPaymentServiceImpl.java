package ks54team01.customer.payment.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ks54team01.customer.payment.domain.CustomerDelivery;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.payment.domain.CustomerPayment;
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
	
	private final ObjectMapper objectMapper;
	
	private final CustomerPaymentMapper customerPaymentMapper;
	
	
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

		String paymentCompletedNo = "payCompleteNo_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
		customerPayment.setPaymentCompletedNo(paymentCompletedNo);
		
		customerPaymentMapper.addPayment(customerPayment);
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
}
