package ks54team01.enterprise.product.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.mapper.EnterpriseProductBenefitMapper;
import ks54team01.enterprise.product.mapper.EnterpriseProductMapper;
import ks54team01.enterprise.product.mapper.EnterpriseProductQuantityMapper;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterpriseProductServiceImpl implements EnterpriseProductService {
	
	private final EnterpriseProductMapper enterpriseProductMapper;
	private final AdminProductMapper adminProductMapper ;
	private final EnterpriseProductBenefitMapper enterpriseProductBenefitMapper;
	private final EnterpriseProductQuantityMapper enterpriseProductQuantityMapper;
	
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
	
	@Override
	public List<AdminProduct>getProductList(){
		
		return adminProductMapper.getProductList();
	};

	
	/*
	 * 입점업체 상품 등록
	 */
	@Override
	public void addSellProduct(EnterpriseProduct enterpriseProduct,
								  List<String> benefitNoList,
					              List<String> benefitDetailList,
	                           EnterpriseProductQuantity quantity) {

	    String sellProductNo = UUID.randomUUID().toString().replace("-", "");
	    LocalDateTime now = LocalDateTime.now();

	    // 1. 메인 상품 세팅
	    enterpriseProduct.setSellProductsNo(sellProductNo);
	    enterpriseProduct.setUseStatus("활성화");
	    enterpriseProduct.setRegisterDate(now);
	    enterpriseProduct.setRevisionDate(now);

	    // 2. INSERT
	    enterpriseProductMapper.addSellProduct(enterpriseProduct);

	    for (int i = 0; i < benefitNoList.size(); i++) {
	        EnterpriseProductBenefit benefit = new EnterpriseProductBenefit();

	        benefit.setSellProductsNo(sellProductNo);
	        benefit.setEntCeoNo(enterpriseProduct.getEntCeoNo());
	        benefit.setEntEmpId(enterpriseProduct.getEntEmpId());
	        benefit.setBenefit(benefitNoList.get(i));
	        benefit.setBenefitDetail(benefitDetailList.get(i));
	        benefit.setUseStatus("활성화");
	        benefit.setRegisterDate(now.toString());
	        benefit.setRevisionDate(now.toString());
	        enterpriseProductBenefitMapper.insertEnterpriseProductBenefit(benefit);
	    }
	  

	    // 4. 재고 세팅
	    quantity.setProductsNo(enterpriseProduct.getProductsNo());
	    quantity.setRegisterDate(now.toString());
	    quantity.setRevisionDate(now.toString());

	    enterpriseProductQuantityMapper.insertEnterpriseProductQuantity(quantity);
	}
}
