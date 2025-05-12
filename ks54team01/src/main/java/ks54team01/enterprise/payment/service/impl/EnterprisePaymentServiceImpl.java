package ks54team01.enterprise.payment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.mapper.EnterprisePaymentMapper;
import ks54team01.enterprise.payment.service.EnterprisePaymentService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterprisePaymentServiceImpl implements EnterprisePaymentService{

	private final EnterprisePaymentMapper enterprisePaymentMapper;
	
	@Override
	public List<EnterprisePayment> getSearchPaymentList(String searchKey, String searchValue) {

		switch (searchKey) {
			case "custNm" 	-> searchKey = "c.cust_nm";
			case "payStatus" 	-> searchKey = "p.payment_status";		
			case "rentalContractNo" 	-> searchKey = "p.rental_contract_no";		
		}
		
		List<EnterprisePayment> enterprisePaymentList = enterprisePaymentMapper.getSearchPaymentList(searchKey, searchValue);
		
		return enterprisePaymentList;
	}
	
	@Override
		public List<EnterprisePayment> getPaymentList() {
			
			List<EnterprisePayment> enterprisePaymentList = enterprisePaymentMapper.getPaymentList();
		
			return enterprisePaymentList;	
			
		}
}
