package ks54team01.admin.payment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.admin.payment.domain.AdminPayment;
import ks54team01.admin.payment.mapper.AdminPaymentCalcMapper;
import ks54team01.admin.payment.mapper.AdminPaymentMapper;
import ks54team01.admin.payment.service.AdminPaymentService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminPaymentServiceImpl implements AdminPaymentService{

	private final AdminPaymentMapper adminPaymentMapper;
	private final AdminPaymentCalcMapper adminPaymentCalcMapper; 
	
	
	
	@Override
	public PageInfo<AdminPayment> getPaymentPageList(Map<String, Object> searchParamMap) {
		
		// 전체 행 개수 조회
		int contentRowCount = adminPaymentMapper.getPaymentCount(searchParamMap);
		
		List<AdminPayment> transferBoardList = adminPaymentMapper.getPaymentPageList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	
	
	
	@Override
	public List<AdminMonthlyFee> getAdminPaymentCalc(String ceoCode) {
		
		List<AdminMonthlyFee> getAdminMonthlyFees = adminPaymentCalcMapper.getAdminPaymentCalc(ceoCode);
	
		return getAdminMonthlyFees;
	}
	@Override
	public List<AdminMonthlyFee> getAdminPaymentCalc(String ceoCode, String settlementMonth) {
		
		List<AdminMonthlyFee> getAdminMonthlyFees = adminPaymentCalcMapper.getAdminPaymentCalc(ceoCode, settlementMonth);
		
		return getAdminMonthlyFees;
	}
	
	
	@Override
	public List<AdminFee> getAdminPayFee(String ceoCode, String settlementMonth) {
	
		List<AdminFee> getAdminPayFee = adminPaymentMapper.getSearchEntFee(ceoCode, settlementMonth);
		
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
	
	
}
