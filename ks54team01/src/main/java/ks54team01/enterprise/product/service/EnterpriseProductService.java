package ks54team01.enterprise.product.service;

import java.util.List;

import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

public interface EnterpriseProductService {
	
	// 재고 조회
	List<EnterpriseProductQuantity> getQuantityList();
}
