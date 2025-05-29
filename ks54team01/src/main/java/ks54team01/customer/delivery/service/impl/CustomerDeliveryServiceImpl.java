package ks54team01.customer.delivery.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.customer.delivery.mapper.CustomerDeliveryMapper;
import ks54team01.customer.delivery.service.CustomerDeliveryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerDeliveryServiceImpl implements CustomerDeliveryService {

	private final CustomerDeliveryMapper customerDeliveryMapper;
	
	
	@Override
	public boolean removeDeliveryList(String delNo) {
		
		int referCount = customerDeliveryMapper.countDelInfoReference(delNo);
		
		if(referCount > 0) {
			
			return false;
		}
		
		int deleted = customerDeliveryMapper.removeDeliveryList(delNo);
		
		boolean isDel = deleted > 0 ? true : false;
		return isDel;
		
	}
	
	
	@Override
	public void modifyDeliveryList(CustomerDeliveryList modifyDeliveryList) {

		if("기본배송지".equals(modifyDeliveryList.getPrimaryLocation())) {
			customerDeliveryMapper.modifyPrimaryLocation(modifyDeliveryList.getCustId());
		}
		
		
		String formatPhone = formatPhoneNumber(modifyDeliveryList.getRecipientPhone());
		modifyDeliveryList.setRecipientPhone(formatPhone);
		
		customerDeliveryMapper.modifyDeliveryList(modifyDeliveryList);
		
	}
	
	
	
	
	@Override
	public void addDeliveryList(CustomerDeliveryList customerDeliveryList) {

		// 기본배송지 선택시 이미 db에 있으면 db에 있는값 추가배송지로 변경
		if("기본배송지".equals(customerDeliveryList.getPrimaryLocation())) {
			customerDeliveryMapper.modifyPrimaryLocation(customerDeliveryList.getCustId());
		}

		// 전화번호 받으면 000-0000-0000 식으로 변경
		String formatPhone = formatPhoneNumber(customerDeliveryList.getRecipientPhone());
		customerDeliveryList.setRecipientPhone(formatPhone);
		
		// pk값 랜덤으로 나오게 설정(delNo_ + 랜덤코드8자리)
		String delNo =  "del_no_" + UUID.randomUUID().toString().replace("-", "");
		customerDeliveryList.setDelNo(delNo);
			
		customerDeliveryMapper.addDeliveryList(customerDeliveryList);
	}
	
	
	private String formatPhoneNumber(String phone) {
		
		if(phone == null) return "";
		
		phone = phone.replaceAll("[^0-9]", "");
	    
		if(phone.length() == 11) {
			return phone.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
    	}
		return phone;
	}
	
	
	
	
	@Override
		public List<CustomerDeliveryList> getDeliveryList(String custId) {

			List<CustomerDeliveryList> customerDeliveryList = customerDeliveryMapper.getDeliveryList(custId);
		
			return customerDeliveryList;
		}

	
	
	
	
}
