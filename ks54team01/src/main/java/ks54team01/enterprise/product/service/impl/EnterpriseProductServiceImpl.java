package ks54team01.enterprise.product.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.mapper.EnterpriseProductMapper;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterpriseProductServiceImpl implements EnterpriseProductService {
	
	private final EnterpriseProductMapper enterpriseProductMapper;
	private final AdminProductMapper adminProductMapper ;
	
	@Override
	public List<EnterpriseProductQuantity> getQuantityList() {
			
	    List<EnterpriseProductQuantity> enterpriseQuantityList = enterpriseProductMapper.getQuantityList();
		
		return enterpriseQuantityList;
	}
	
	// 입점 업체 등록 상품 리스트
	@Override
	public List<EnterpriseProduct> getSellProductList() {

		return enterpriseProductMapper.getSellProductList();
	}
	
	
	public List<AdminProduct>getProductList(){
		
		return adminProductMapper.getProductList();
	};
	
	/*
	 * 입점업체 상품 등록
	 */
	@Override
	public void addSellProduct(EnterpriseProduct enterpriseProduct) {
		
		// Pk 랜덤 생성
		String sellProductNo = UUID.randomUUID().toString().replace("-", "");
		enterpriseProduct.setUseStatus("활성화");
		
		enterpriseProduct.setSellProductsNo(sellProductNo);
		enterpriseProductMapper.addEnterpriseProduct(enterpriseProduct);
		
	}
}
