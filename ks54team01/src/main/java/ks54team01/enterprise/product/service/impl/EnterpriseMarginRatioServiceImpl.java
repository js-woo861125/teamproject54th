package ks54team01.enterprise.product.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;
import ks54team01.enterprise.product.mapper.EnterpriseMarginRatioMapper;
import ks54team01.enterprise.product.service.EnterpriseMarginRatioService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterpriseMarginRatioServiceImpl implements EnterpriseMarginRatioService {
	
	private final EnterpriseMarginRatioMapper enterpriseMarginRatioMapper;
	
	/**
	 * 업체별 마진율 최초 등록
	 */
	@Override
	public void addEnterpriseMarginRatio(EnterpriseMarginRatio enterpriseMarginRatio) {
		
		int isAdd = enterpriseMarginRatioMapper.existMarginRatioByPeriod(enterpriseMarginRatio);
		
		if(isAdd != 1) {
			String marginRatioNum =  "margin_ratio_" + UUID.randomUUID().toString().replace("-", "");
			enterpriseMarginRatio.setMarginRatioNum(marginRatioNum);		
			enterpriseMarginRatioMapper.addEnterpriseMarginRatio(enterpriseMarginRatio);
		}
	}
	
	/**
	 * 업체별 마진율 사용유무 변경 (사실상 삭제 기능도 포함)
	 */
	@Override
	public void modifyMarginRatioUseStatus(String marginRatioNum, String useStatus) {
		
		enterpriseMarginRatioMapper.modifyMarginRatioUseStatus(marginRatioNum, useStatus);
	
	}
	
	
	/**
	 * 업체별 마진율 변경
	 */
	@Override
	public void modifyEnterpriseMarginRatio(List<EnterpriseMarginRatio> list) {

		list.stream()
	    .filter(item -> item != null && item.getMarginRatio() != null)
	    .forEach(item -> enterpriseMarginRatioMapper.modifyEnterpriseMarginRatio(item));
	}
	
	/**
	 * 업체별 마진율 조회
	 */
	@Override
	public List<EnterpriseMarginRatio> getEnterpriseMarginRatio() {
		
		List<EnterpriseMarginRatio> enterpriseMarginRatio = enterpriseMarginRatioMapper.getEnterpriseMarginRatio();
		
		return enterpriseMarginRatio;
	}
	
}
