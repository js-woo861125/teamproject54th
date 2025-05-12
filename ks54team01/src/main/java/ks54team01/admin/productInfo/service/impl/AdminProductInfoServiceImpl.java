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
	
	// 상품정보 카테고리 수정
	@Override
	public void modifyCategory(ProductInfoCategory productInfoCategory) {
		
		adminProductInfoMapper.modifyCategory(productInfoCategory);
	}
	
	// 상품정보 카테고리별/상세스펙 등록
	@Override
	public void addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec) {
		log.info("상품등록 전 : {}", ProductInfoCategorySpec);
		
		adminProductInfoMapper.addCategorySpec(ProductInfoCategorySpec);
		
		log.info("상품등록 후 : {}", ProductInfoCategorySpec);
	}
	
	// 상품정보 전체혜택 등록
	@Override
	public void addBenefit(ProductInfoBenefit productInfoBenefit) {
		
		log.info("상품등록 전 : {}", productInfoBenefit);
		
		adminProductInfoMapper.addBenefit(productInfoBenefit);
		
		log.info("상품등록 후 : {}", productInfoBenefit);
	}
	
	// 상품정보 모델 등록
	@Override
	public void addModel(ProductInfoModel productInfoModel) {
		
		log.info("상품등록 전 : {}", productInfoModel);
		
		adminProductInfoMapper.addModel(productInfoModel);
		
		log.info("상품등록 후 : {}", productInfoModel);
	}
	
	// 상품정보 품목 등록
	@Override
	public void addItem(ProductInfoItem productInfoItem) {
		
		log.info("상품등록 전 : {}", productInfoItem);
		
		adminProductInfoMapper.addItem(productInfoItem);
		
		log.info("상품등록 후 : {}", productInfoItem);
	}
	
	// 상품정보 브랜드 등록
	@Override
	public void addBrand(ProductInfoBrand productInfoBrand) {
		
		log.info("상품등록 전 : {}", productInfoBrand);
		
		adminProductInfoMapper.addBrand(productInfoBrand);
		
		log.info("상품등록 후 : {}", productInfoBrand);
	}
	
	// 상품정보 카테고리 등록
	@Override
	public void addCategory(ProductInfoCategory productInfoCategory) {
		
		log.info("상품등록 전 : {}", productInfoCategory);
		
		adminProductInfoMapper.addCategory(productInfoCategory);
		
		log.info("상품등록 후 : {}", productInfoCategory);
	}
	
	// 상품정보 카테고리별/상세스펙 목록 조회
	@Override
	public List<ProductInfoCategorySpec> getCategorySpecList() {
		
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoMapper.getCategorySpecList();
		
		return categorySpecList;
	}
	
	// 상품정보 전체혜택 목록 조회
	@Override
	public List<ProductInfoBenefit> getBenefitList() {
		
		List<ProductInfoBenefit> benefitList = adminProductInfoMapper.getBenefitList();
		
		return benefitList;
	}
	
	// 상품정보 모델 목록 조회
	@Override
	public List<ProductInfoModel> getModelList() {
		
		List<ProductInfoModel> modelList = adminProductInfoMapper.getModelList();
		
		return modelList;
	}
	
	// 상품정보 품목 목록 조회
	@Override
	public List<ProductInfoItem> getItemList() {
		
		List<ProductInfoItem> itemList = adminProductInfoMapper.getItemList();
		
		return itemList;
	}
	
	// 상품정보 브랜드 목록 조회
	@Override
	public List<ProductInfoBrand> getBrandList() {
		
		List<ProductInfoBrand> brandList = adminProductInfoMapper.getBrandList();
		
		return brandList;
	}
	
	// 상품정보 카테고리 목록 조회
	@Override
	public List<ProductInfoCategory> getCategoryList() {
		
	List<ProductInfoCategory> categoryList = adminProductInfoMapper.getCategoryList();
	
		return categoryList;
	}
}
