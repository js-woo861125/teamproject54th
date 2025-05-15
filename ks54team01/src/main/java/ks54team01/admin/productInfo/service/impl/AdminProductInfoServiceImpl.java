package ks54team01.admin.productInfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.domain.ProductInfoModelSpec;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import ks54team01.admin.productInfo.service.AdminProductInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminProductInfoServiceImpl implements AdminProductInfoService {

	// DI 의존성 주입
	private final AdminProductInfoMapper adminProductInfoMapper;
	
	// 상품정보 수정
	@Override
	public void modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec) {
		
		adminProductInfoMapper.modifyCategorySpec(productInfoCategorySpec);
	}
	
	@Override
	public void modifyBenefit(ProductInfoBenefit productInfoBenefit) {
		
		adminProductInfoMapper.modifyBenefit(productInfoBenefit);
	}
	
	@Override
	public void modifyModel(ProductInfoModel productInfoModel) {
		
		adminProductInfoMapper.modifyModel(productInfoModel);
	}
	
	@Override
	public void modifyItem(ProductInfoItem productInfoItem) {
		
		adminProductInfoMapper.modifyItem(productInfoItem);
	}
	
	@Override
	public void modifyBrand(ProductInfoBrand productInfoBrand) {
		
		adminProductInfoMapper.modifyBrand(productInfoBrand);
	}
	
	@Override
	public void modifyCategory(ProductInfoCategory productInfoCategory) {
		
		adminProductInfoMapper.modifyCategory(productInfoCategory);
	}
	
	// 상품정보 조회
	@Override
	public ProductInfoCategorySpec getCategorySpecInfoByNo(String categorySpecyNo) {
		
		return adminProductInfoMapper.getCategorySpecInfoByNo(categorySpecyNo);
	}
	
	@Override
	public ProductInfoBenefit getBenefitInfoByNo(String benefitNo) {
		
		return adminProductInfoMapper.getBenefitInfoByNo(benefitNo);
	}
	
	@Override
	public ProductInfoModel getModelInfoByNo(String modelNo) {
		
		return adminProductInfoMapper.getModelInfoByNo(modelNo);
	}
	
	@Override
	public ProductInfoItem getItemInfoByNo(String itemNo) {
		
		return adminProductInfoMapper.getItemInfoByNo(itemNo);
	}
	
	@Override
	public ProductInfoBrand getBrandInfoByNo(String brandNo) {
		
		return adminProductInfoMapper.getBrandInfoByNo(brandNo);
	}
	
	@Override
	public ProductInfoCategory getCategoryInfoByNo(String categoryNo) {
		
		return adminProductInfoMapper.getCategoryInfoByNo(categoryNo);
	}
	
	// 상품정보 등록
	@Override
	public void addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec) {
		log.info("상품등록 전 : {}", ProductInfoCategorySpec);
		
		adminProductInfoMapper.addCategorySpec(ProductInfoCategorySpec);
		
		log.info("상품등록 후 : {}", ProductInfoCategorySpec);
	}

	@Override
	public void addBenefit(ProductInfoBenefit productInfoBenefit) {
		
		log.info("상품등록 전 : {}", productInfoBenefit);
		
		adminProductInfoMapper.addBenefit(productInfoBenefit);
		
		log.info("상품등록 후 : {}", productInfoBenefit);
	}

	@Override
	public void addModel(ProductInfoModel productInfoModel) {
		
		log.info("상품등록 전 : {}", productInfoModel);
		
		adminProductInfoMapper.addModel(productInfoModel);
		
		log.info("상품등록 후 : {}", productInfoModel);
	}

	@Override
	public void addItem(ProductInfoItem productInfoItem) {
		
		log.info("상품등록 전 : {}", productInfoItem);
		
		adminProductInfoMapper.addItem(productInfoItem);
		
		log.info("상품등록 후 : {}", productInfoItem);
	}

	@Override
	public void addBrand(ProductInfoBrand productInfoBrand) {
		
		log.info("상품등록 전 : {}", productInfoBrand);
		
		adminProductInfoMapper.addBrand(productInfoBrand);
		
		log.info("상품등록 후 : {}", productInfoBrand);
	}
	
	@Override
	public void addCategory(ProductInfoCategory productInfoCategory) {
		
		log.info("상품등록 전 : {}", productInfoCategory);
		
		adminProductInfoMapper.addCategory(productInfoCategory);
		
		log.info("상품등록 후 : {}", productInfoCategory);
	}
	
	// 상품정보 목록 조회
	@Override
	public List<ProductInfoModelSpec> getModelSpecList() {
		
		List<ProductInfoModelSpec> modelSpecList = adminProductInfoMapper.getModelSpecList();
		
		return modelSpecList;
	}
	
	@Override
	public List<ProductInfoCategorySpec> getCategorySpecList() {
		
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoMapper.getCategorySpecList();
		
		return categorySpecList;
	}

	@Override
	public List<ProductInfoBenefit> getBenefitList() {
		
		List<ProductInfoBenefit> benefitList = adminProductInfoMapper.getBenefitList();
		
		return benefitList;
	}

	@Override
	public List<ProductInfoModel> getModelList() {
		
		List<ProductInfoModel> modelList = adminProductInfoMapper.getModelList();
		
		return modelList;
	}

	@Override
	public List<ProductInfoItem> getItemList() {
		
		List<ProductInfoItem> itemList = adminProductInfoMapper.getItemList();
		
		return itemList;
	}

	@Override
	public List<ProductInfoBrand> getBrandList() {
		
		List<ProductInfoBrand> brandList = adminProductInfoMapper.getBrandList();
		
		return brandList;
	}

	@Override
	public List<ProductInfoCategory> getCategoryList() {
		
	List<ProductInfoCategory> categoryList = adminProductInfoMapper.getCategoryList();
	
		return categoryList;
	}
}
