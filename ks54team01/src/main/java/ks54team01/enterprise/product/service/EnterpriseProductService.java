package ks54team01.enterprise.product.service;

import java.util.List;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

public interface EnterpriseProductService {
	 

	// 재고 조회
	List<EnterpriseProductQuantity> getQuantityList();
	
	// 입점업체 등록 상품리스트
	List<EnterpriseProduct> getSellProductList();
	
	// 입점업체 플랫폼 등록 상품 조회리스트
	List<AdminProduct> getProductList();
	
	 void addSellProduct(
		        EnterpriseProduct product,
		        List<String> benefitNoList,
                List<String> benefitDetailList,
		        EnterpriseProductQuantity quantity
		    );
	
	
}
