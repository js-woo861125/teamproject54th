package ks54team01.admin.payment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.admin.payment.domain.AdminPayment;
import ks54team01.admin.payment.mapper.AdminPaymentCalcMapper;
import ks54team01.admin.payment.mapper.AdminPaymentMapper;
import ks54team01.admin.payment.service.AdminPaymentService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPaymentServiceImpl implements AdminPaymentService{

	private final AdminPaymentMapper adminPaymentMapper;
	private final AdminPaymentCalcMapper adminPaymentCalcMapper; 
	
	@Override
	public List<AdminMonthlyFee> getAdminPaymentCalc(String ceoCode) {
		
		List<AdminMonthlyFee> getAdminMonthlyFees = adminPaymentCalcMapper.getAdminPaymentCalc(ceoCode);
		
		return getAdminMonthlyFees;
	}
	
	
	@Override
	public List<AdminFee> getAdminPayFee(String ceoCode) {
	
		List<AdminFee> getAdminPayFee = adminPaymentMapper.getSearchEntFee(ceoCode);
		
		return getAdminPayFee;
	}
	
	
	
	@Override
	public List<AdminEntList> getSearchEnt(String searchKey, String searchValue) {
		
		switch (searchKey) {
	    case "entCeoNo" -> searchKey = "ec.ent_ceo_no";
	    case "entBrNo" -> searchKey = "ec.ent_brno";
	    case "entName" -> searchKey = "ec.ent_nm";
		}
		
		List<AdminEntList> entList = adminPaymentMapper.getSearchEnt(searchKey, searchValue);
		
		return entList;
	}
	
	
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
