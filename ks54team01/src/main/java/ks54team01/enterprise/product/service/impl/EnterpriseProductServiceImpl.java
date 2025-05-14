package ks54team01.enterprise.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.mapper.EnterpriseProductMapper;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterpriseProductServiceImpl implements EnterpriseProductService {
	
	private final EnterpriseProductMapper enterpriseProductMapper;
	
	@Override
	public List<EnterpriseProductQuantity> getQuantityList() {
			
	    List<EnterpriseProductQuantity> enterpriseQuantityList = enterpriseProductMapper.getQuantityList();
		
		return enterpriseQuantityList;
	}
}
