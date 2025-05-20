package ks54team01.enterprise.product.service.impl;

import java.util.List;

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
	
	@Override
	public List<EnterpriseMarginRatio> getEnterpriseMarginRatio() {
		
		List<EnterpriseMarginRatio> enterpriseMarginRatio = enterpriseMarginRatioMapper.getEnterpriseMarginRatio();
		
		return enterpriseMarginRatio;
	}
	
}
