package ks54team01.enterprise.product.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.enterprise.product.domain.EnterprisePenaltyCalculate;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.domain.EnterpriseSellProductRequest;
import ks54team01.enterprise.product.mapper.EnterprisePenaltyCalculateMapper;
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
	private final AdminProductMapper adminProductMapper;
	private final EnterpriseProductBenefitMapper enterpriseProductBenefitMapper;
	private final EnterpriseProductQuantityMapper enterpriseProductQuantityMapper;
	private final EnterprisePenaltyCalculateMapper enterprisePenaltyCalculateMapper;

	@Override
	public List<EnterpriseProduct> searchSellProductList(String searchKey, String searchValue, String categoryNo,
			String status) {

		return enterpriseProductMapper.searchSellProductList(searchKey, searchValue, categoryNo, status);
	}

	@Override
	public List<EnterpriseProductQuantity> getQuantityList(String searchKey,String searchValue) {

		List<EnterpriseProductQuantity> enterpriseQuantityList = enterpriseProductMapper.getQuantityList();

		return enterpriseQuantityList;
	}

	// 입점 업체 등록 상품 리스트
	@Override
	public List<EnterpriseProduct> getSellProductList(String searchKey, String searchValue, String categoryNo,
			String status) {

		return enterpriseProductMapper.getSellProductList();
	}

	@Override
	public List<AdminProduct> getProductList(String searchKey, String searchValue, String categoryNo, String status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchKey", searchKey);
		paramMap.put("searchValue", searchValue);
		paramMap.put("categoryNo", categoryNo);
		paramMap.put("status", status);
		return adminProductMapper.searchProductList(paramMap);
	}

	/*
	 * 입점업체 상품 등록
	 */
	@Override
	@Transactional
	public void addSellProductBatch(List<EnterpriseSellProductRequest> sellProductRequests,
			EnterpriseProductQuantity quantity) {

		// 상품(개월수별) 등록
		for (EnterpriseSellProductRequest req : sellProductRequests) {
			EnterpriseProduct enterpriseProduct = req.getEnterpriseProduct();
			String sellProductNo = UUID.randomUUID().toString().replace("-", "");
			LocalDateTime now = LocalDateTime.now();

			// 상품 중복체크
			int exists = enterpriseProductMapper.countByProduct(enterpriseProduct.getEntCeoNo(),
					enterpriseProduct.getProductsNo(), enterpriseProduct.getPeriod());
			if (exists > 0) {
				throw new IllegalStateException("이미 같은 옵션(입점업체/상품/개월수)으로 등록된 상품이 있습니다: "
						+ enterpriseProduct.getProductsNo() + " / " + enterpriseProduct.getPeriod() + "개월");
			}

			// 1. 메인 상품 세팅
			enterpriseProduct.setSellProductsNo(sellProductNo);
			enterpriseProduct.setUseStatus("사용중");
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
							(benefitDetailList != null && benefitDetailList.size() > i) ? benefitDetailList.get(i)
									: null);
					benefit.setUseStatus("사용중");
					benefit.setRegisterDate(now.toString());
					benefit.setRevisionDate(now.toString());
					enterpriseProductBenefitMapper.insertEnterpriseProductBenefit(benefit);
				}
			}
			EnterprisePenaltyCalculate penalty = new EnterprisePenaltyCalculate();

			penalty.setEntCeoNo(enterpriseProduct.getEntCeoNo());
			penalty.setEntEmpId(enterpriseProduct.getEntEmpId());
			penalty.setSellProductsNo(sellProductNo);
			penalty.setRegisterDate(now.toString());
			penalty.setRevisionDate(now.toString());
			penalty.setPenaltyFeeRatio(req.getPenaltyFeeRatio());
			penalty.setPeriodStart(req.getPeriodStart());
			penalty.setPeriodEnd(req.getPeriodEnd());
			penalty.setUseStatus("사용중");
			enterprisePenaltyCalculateMapper.insertPenaltyCalculate(penalty);

		}

		// 4. 재고 인서트 (한 번만)
		quantity.setRegisterDate(LocalDateTime.now().toString());
		quantity.setRevisionDate(LocalDateTime.now().toString());
		enterpriseProductQuantityMapper.insertEnterpriseProductQuantity(quantity);

	}

	@Override
	@Transactional
	public void modifySellProductBenefits(EnterpriseProduct product, List<String> benefitNoList,
			List<String> benefitDetailList, double penaltyRatio) {
		// 1. 상품 정보 수정 (실판매가/계산가/최종가 등)
		enterpriseProductMapper.updateSellProduct(product);

		EnterprisePenaltyCalculate penalty = new EnterprisePenaltyCalculate();
		penalty.setSellProductsNo(product.getSellProductsNo());
		penalty.setPenaltyFeeRatio(penaltyRatio);

		enterprisePenaltyCalculateMapper.updatePenaltyCalculate(penalty);
		penalty.setSellProductsNo(product.getSellProductsNo());
		penalty.setPenaltyFeeRatio(penaltyRatio);
		enterprisePenaltyCalculateMapper.updatePenaltyCalculate(penalty);

		// 2. 기존 혜택 삭제
		enterpriseProductBenefitMapper.deleteBenefitsBySellProductNo(product.getSellProductsNo());

		// 3. 새로운 혜택 인서트
		for (int i = 0; i < benefitNoList.size(); i++) {
			EnterpriseProductBenefit benefit = new EnterpriseProductBenefit();
			benefit.setSellProductsNo(product.getSellProductsNo());
			benefit.setBenefit(benefitNoList.get(i));
			benefit.setBenefitDetail(benefitDetailList.get(i));
			benefit.setEntCeoNo(product.getEntCeoNo());
			benefit.setEntEmpId(product.getEntEmpId());
			benefit.setUseStatus("사용중");

			enterpriseProductBenefitMapper.insertEnterpriseProductBenefit(benefit);
		}
	}

	@Override
	public EnterpriseProduct getProductByNo(String sellProductsNo) {
		return enterpriseProductMapper.getProductByNo(sellProductsNo);
	}

	@Override
	public List<EnterpriseProductBenefit> getBenefitListBySellProductNo(String sellProductsNo) {
		return enterpriseProductBenefitMapper.selectBenefitsBySellProductNo(sellProductsNo);
	}

	@Override
	public EnterprisePenaltyCalculate getPenaltyCalculateByNo(String sellProductsNo) {
		return enterprisePenaltyCalculateMapper.selectPenaltyCalculateByNo(sellProductsNo);
	}

	@Override
	public void setSaleStoppage(String sellProductsNo) {
		enterpriseProductMapper.setSaleStoppage(sellProductsNo);
		;
	}

	@Override
	public void unsetSaleStoppage(String sellProductsNo) {
		enterpriseProductMapper.unsetSaleStoppage(sellProductsNo);

	}

	@Transactional
	@Override
	public boolean updateQuantity(String productsNo, int quantity) {
		int updated = enterpriseProductMapper.updateQuantity(productsNo, quantity);
		return updated > 0;
	}

}
