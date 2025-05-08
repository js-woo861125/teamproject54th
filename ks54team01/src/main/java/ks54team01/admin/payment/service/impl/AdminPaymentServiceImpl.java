package ks54team01.admin.payment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.payment.domain.AdminPayment;
import ks54team01.admin.payment.mapper.AdminPaymentMapper;
import ks54team01.admin.payment.service.AdminPaymentService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPaymentServiceImpl implements AdminPaymentService{

	private final AdminPaymentMapper adminPaymentMapper;
	
	
	@Override
	public List<AdminPayment> getSearchPaymentList(String searchKey, String searchValue) {

		switch (searchKey) {
		case "custNm" 	-> searchKey = "c.cust_nm";
		case "payStatus" 	-> searchKey = "p.payment_status";		
	}
	List<AdminPayment> adminDeliveryList = adminPaymentMapper.getSearchPaymentList(searchKey, searchValue);
	
	return adminDeliveryList;
	}
	
	
	
	@Override
	public List<AdminPayment> getPaymentList() {
		
		List<AdminPayment> adminPaymentList = adminPaymentMapper.getPaymentList();
		
		return adminPaymentList;
	}
}
