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
import ks54team01.enterprise.product.domain.EnterpriseSellProductRequest;
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
	 @Transactional
	 public void addSellProductBatch(List<EnterpriseSellProductRequest> sellProductRequests, EnterpriseProductQuantity quantity) {

	        // 상품(개월수별) 등록
	        for (EnterpriseSellProductRequest req : sellProductRequests) {
	            EnterpriseProduct enterpriseProduct = req.getEnterpriseProduct();
	            String sellProductNo = UUID.randomUUID().toString().replace("-", "");
	            LocalDateTime now = LocalDateTime.now();

	            // 1. 메인 상품 세팅
	            enterpriseProduct.setSellProductsNo(sellProductNo);
	            enterpriseProduct.setUseStatus("활성화");
	            enterpriseProduct.setRegisterDate(now);
	            enterpriseProduct.setRevisionDate(now);

	            // 2. 상품 인서트
	            enterpriseProductMapper.addSellProduct(enterpriseProduct);

	            // 3. 혜택 리스트 인서트
	            List<String> benefitNoList = req.getBenefitNoList();
	            List<String> benefitDetailList = req.getBenefitDetailList();
	            if (benefitNoList != null) {
	                for (int i = 0; i < benefitNoList.size(); i++) {
	                    EnterpriseProductBenefit benefit = new EnterpriseProductBenefit();
	                    benefit.setSellProductsNo(sellProductNo);
	                    benefit.setEntCeoNo(enterpriseProduct.getEntCeoNo());
	                    benefit.setEntEmpId(enterpriseProduct.getEntEmpId());
	                    benefit.setBenefit(benefitNoList.get(i));
	                    benefit.setBenefitDetail(
	                        (benefitDetailList != null && benefitDetailList.size() > i) ? benefitDetailList.get(i) : null
	                    );
	                    benefit.setUseStatus("활성화");
	                    benefit.setRegisterDate(now.toString());
	                    benefit.setRevisionDate(now.toString());
	                    enterpriseProductBenefitMapper.insertEnterpriseProductBenefit(benefit);
	                }
	            }
	        }

	        // 4. 재고 인서트 (한 번만)
	        quantity.setRegisterDate(LocalDateTime.now().toString());
	        quantity.setRevisionDate(LocalDateTime.now().toString());
	        enterpriseProductQuantityMapper.insertEnterpriseProductQuantity(quantity);
	    }
}
