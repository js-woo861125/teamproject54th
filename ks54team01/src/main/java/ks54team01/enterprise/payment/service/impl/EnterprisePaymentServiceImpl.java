package ks54team01.enterprise.payment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.payment.domain.EnterprisePayment;
import ks54team01.enterprise.payment.domain.EnterprisePaymentDetail;
import ks54team01.enterprise.payment.mapper.EnterprisePaymentMapper;
import ks54team01.enterprise.payment.service.EnterprisePaymentService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterprisePaymentServiceImpl implements EnterprisePaymentService{

	private final EnterprisePaymentMapper enterprisePaymentMapper;
	
	
	
	
	@Override
	public List<EnterprisePaymentDetail> getPaymentDetailListByContractNoAndStatus(String rentalContractNo, String unpaidStatus) {
	    
		List<EnterprisePaymentDetail> getPaymentListByContNoAndStatus = enterprisePaymentMapper.getPaymentDetailListByContractNoAndStatus(rentalContractNo, unpaidStatus);
		
		return getPaymentListByContNoAndStatus;
	}

	@Override
	public List<EnterprisePaymentDetail> getPaymentDetailListByContractNo(String rentalContractNo) {

		List<EnterprisePaymentDetail> getPaymentListByContNo = enterprisePaymentMapper.getPaymentDetailListByContractNo(rentalContractNo);
		
		return getPaymentListByContNo;
	}
	
	
	
	@Override
	public PageInfo<EnterprisePayment> getPaymentList(Map<String, Object> searchParamMap) {
		// 전체 행 개수 조회
		int contentRowCount = enterprisePaymentMapper.getPaymentCount(searchParamMap);
		
		List<EnterprisePayment> transferBoardList = enterprisePaymentMapper.getPaymentList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	
}
