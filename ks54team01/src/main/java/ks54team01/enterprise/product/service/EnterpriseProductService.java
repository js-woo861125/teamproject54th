package ks54team01.enterprise.product.service;

import java.util.List;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.enterprise.product.domain.EnterprisePenaltyCalculate;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.domain.EnterpriseSellProductRequest;

public interface EnterpriseProductService {
	 

	// 재고 조회
	List<EnterpriseProductQuantity> getQuantityList();
	
	// 입점업체 등록 상품리스트
	List<EnterpriseProduct> getSellProductList();
	
	// 입점업체 플랫폼 등록 상품 조회리스트
	List<AdminProduct> getProductList();
	
	// 입점업체 상품 등록
	void addSellProductBatch(List<EnterpriseSellProductRequest> sellProductRequests, EnterpriseProductQuantity quantity);
	
	// 단일 상품 조회
	EnterpriseProduct getProductByNo(String sellProductsNo);
	
	// 입점업체 위약금 조회
	EnterprisePenaltyCalculate getPenaltyCalculateByNo(String penaltyCalculateNo);

	// 단일 상품의 혜택 리스트 조회
	List<EnterpriseProductBenefit> getBenefitListBySellProductNo(String sellProductsNo);
	
	
	// 입점업체 등록 상품 수정
	  void modifySellProductBenefits(
		EnterpriseProduct product,
		List<String> benefitNoList,
		List<String> benefitDetailList,
		double penaltyRatio
		    );
}
