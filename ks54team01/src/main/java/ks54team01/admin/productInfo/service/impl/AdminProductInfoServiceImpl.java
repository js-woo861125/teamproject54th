package ks54team01.admin.productInfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * 상품정보 사용유무 필터링
	 */
	// 카테고리별/상세스펙 사용유무 필터링
	@Override
	public List<ProductInfoCategorySpec> getUsableCateogrySpecList() {
		
		return adminProductInfoMapper.getUsableCateogrySpecList();
	}
	// 모델 사용유무 필터링
	@Override
	public List<ProductInfoModel> getUsableModelList() {
		
		return adminProductInfoMapper.getUsableModelList();
	}
	// 품목 사용유무 필터링
	@Override
	public List<ProductInfoItem> getUsableItemList() {
		
		return adminProductInfoMapper.getUsableItemList();
	}
	// 브랜드 사용유무 필터링
	@Override
	public List<ProductInfoBrand> getUsableBrandList() {
		
		return adminProductInfoMapper.getUsableBrandList();
	}
	// 카테고리 사용유무 필터링
	@Override
	public List<ProductInfoCategory> getUsableCategoryList() {
		
		return adminProductInfoMapper.getUsableCategoryList();
	}
	
	/**
	 *  상품정보 등록 조회
	 */
	// 카테고리별/상세스펙코드로 등록된 모델별/상세스펙 조회
	@Override
	public int countModelSpecsBySpecNo(String specNo) {
		
		return adminProductInfoMapper.countModelSpecsBySpecNo(specNo);
	}
	// 혜택코드로 등록된 입점업체 혜택상세 조회
	@Override
	public int countBenefitDetailsByBenefitNo(String benefitNo) {
		
		return adminProductInfoMapper.countBenefitDetailsByBenefitNo(benefitNo);
	}
	// 모델코드로 등록된 모델별/상세스펙 조회 
	@Override
	public int countModelSpecsByModelNo(String modelNo) {
		
		return adminProductInfoMapper.countModelSpecsByModelNo(modelNo);
	}
	// 품목코드로 등록된 모델 조회 
	@Override
	public int countModelsByItemNo(String itemNo) {
		
		return adminProductInfoMapper.countModelsByItemNo(itemNo);
	}
	// 브랜드코드로 등록된 모델 조회
	@Override
	public int countModelsByBrandNo(String brandNo) {
		
		return adminProductInfoMapper.countModelsByBrandNo(brandNo);
	}
	// 카테고리코드로 등록된 카테고리별/상세스펙 조회
	@Override
	public int countSpecsByCategoryNo(String categoryNo) {
		
		return adminProductInfoMapper.countSpecsByCategoryNo(categoryNo);
	}
	// 카테고리코드로 등록된 품목 조회
	@Override
	public int countItemsByCategoryNo(String categoryNo) {
		
		return adminProductInfoMapper.countItemsByCategoryNo(categoryNo);
	}
	
	// 사용유무 상태 변경
	@Override
	public void updateModelSpecUseStatus(String modelSpecNo, String useStatus) {
		
		adminProductInfoMapper.updateModelSpecUseStatus(modelSpecNo, useStatus);	
	}
	
	@Override
	public void updateCategorySpecUseStatus(String specNo, String useStatus) {
		
		adminProductInfoMapper.updateCategorySpecUseStatus(specNo, useStatus);	
	}
	
	@Override
	public void updateBenefitUseStatus(String benefitNo, String useStatus) {
		
		adminProductInfoMapper.updateBenefitUseStatus(benefitNo, useStatus);
	}
	
	@Override
	public void updateModelUseStatus(String modelNo, String useStatus) {
		
		adminProductInfoMapper.updateModelUseStatus(modelNo, useStatus);
	}
	
	@Override
	public void updateItemUseStatus(String itemNo, String useStatus) {
		
		adminProductInfoMapper.updateItemUseStatus(itemNo, useStatus);
	}
	
	@Override
	public void updateBrandUseStatus(String brandNo, String useStatus) {
		
		adminProductInfoMapper.updateBrandUseStatus(brandNo, useStatus);
	}
	
	@Override
	public void updateCategoryUseStatus(String categoryNo, String useStatus) {

		adminProductInfoMapper.updateCategoryUseStatus(categoryNo, useStatus);	
	}
	
	// 상품정보 검색
	@Override
	public List<ProductInfoModelSpec> getSearchModelSpec(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "modelSpecName" -> searchKey = "msc.spec_content";
		case "modelInfo.modelName" -> searchKey = "m.model_nm";
		case "specInfo.specName" -> searchKey = "ps.spec_nm";
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}
		List<ProductInfoModelSpec> modelSpecList = adminProductInfoMapper.getSearchModelSpec(searchKey, searchValue, useStatus);
		return modelSpecList;
	}
	
	@Override
	public List<ProductInfoCategorySpec> getSearchCategorySpec(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "specName" -> searchKey = "spec_nm";
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoMapper.getSearchCategorySpec(searchKey, searchValue, useStatus);
		
		return categorySpecList;
	}
	@Override
	public List<ProductInfoBenefit> getSearchBenefit(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "benefitName" -> searchKey = "benefit_nm";
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoBenefit> benefitList = adminProductInfoMapper.getSearchBenefit(searchKey, searchValue, useStatus);
		
		return benefitList;	
	}
	
	@Override
	public List<ProductInfoModel> getSearchModel(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "modelName" -> searchKey = "model_nm";
		case "brandInfo.brandName" -> searchKey ="b.brand_nm";
		case "itemInfo.itemName" -> searchKey ="i.item_nm";
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoModel> modelList = adminProductInfoMapper.getSearchModel(searchKey, searchValue, useStatus);
		
		return modelList;
	}
	
	@Override
	public List<ProductInfoItem> getSearchItem(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "itemName" -> searchKey = "item_nm";
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoItem> itemList = adminProductInfoMapper.getSearchItem(searchKey, searchValue, useStatus);
				
		return itemList;
	}
	@Override
	public List<ProductInfoBrand> getSearchBrand(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "brandName" -> searchKey = "brand_nm";	
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoBrand> brandList = adminProductInfoMapper.getSearchBrand(searchKey, searchValue, useStatus);
		
		return brandList;
	}
	@Override
	public List<ProductInfoCategory> getSearchCategory(String searchKey, String searchValue, String useStatus) {
		
		switch (searchKey) {
		case "lgCategory" 	-> searchKey = "large_category";
		case "mdCategory" 	-> searchKey = "middle_category";
		case "smCategory" 	-> searchKey = "small_category";	
		}
		
		if (useStatus.isBlank()) {
			useStatus = null; 
		}		
		List<ProductInfoCategory> categoryList = adminProductInfoMapper.getSearchCategory(searchKey, searchValue, useStatus);
		
		return categoryList;
	}
	
	// 상품정보 삭제
	@Override
	public boolean removeModelSpecSpecInfoByNo(String modelSpecNo) {
		
		int delCount = 0;
		
		delCount += adminProductInfoMapper.removeModelSpecSpecInfoByNo(modelSpecNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeCategorySpecInfoByNo(String specNo) {
		
		// 1. 해당 스펙이 등록된 모델스펙이 있는지 확인
		int modelSpecCount = adminProductInfoMapper.countModelSpecsBySpecNo(specNo);
		
		if (modelSpecCount > 0) {
			return false; // 스펙이 모델스펙에 등록되어 있어 삭제 불가
		}
		
		// 2. 등록된 모델스펙이 없으면 삭제
		int delCount = adminProductInfoMapper.removeCategorySpecInfoByNo(specNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeBenefitInfoByNo(String benefitNo) {
		
		// 1. 해당 혜택이 등록된 입점업체 혜택상세가 있는지 확인
		int benefitDetailCount = adminProductInfoMapper.countBenefitDetailsByBenefitNo(benefitNo);
		
		if (benefitDetailCount > 0) {
			return false; // 혜택이 입점업체 혜택상세에 등록되어 있어 삭제 불가
		}
		
		// 2. 등록된 혜택상세가 없으면 삭제
		int delCount = adminProductInfoMapper.removeBenefitInfoByNo(benefitNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeModelInfoByNo(String modelNo) {
		
		// 1. 해당 모델이 등록된 모델스펙이 있는지 확인
		int modelSpecCount = adminProductInfoMapper.countModelSpecsByModelNo(modelNo);
		
		if (modelSpecCount > 0) {
			return false; // 모델이 모델스펙이 등록되어 있어 삭제 불가
		}
		
		// 2. 등록된 모델스펙이 없으면 삭제
		int delCount = adminProductInfoMapper.removeModelInfoByNo(modelNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeItemInfoByNo(String itemNo) {
		
		// 1. 해당 품목이 등록된 모델이 있는지 확인
		int modelCount = adminProductInfoMapper.countModelsByItemNo(itemNo);
		
		if (modelCount > 0) {
			return false; // 품목이 모델에 등록되어 있어 삭제 불가
		}
		
		// 2. 등록된 모델이 없으면 삭제
		int delCount = adminProductInfoMapper.removeItemInfoByNo(itemNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeBrandInfoByNo(String brandNo) {
		
		// 1. 해당 브랜드가 등록된 모델이 있는지 확인
		int modelCount = adminProductInfoMapper.countModelsByBrandNo(brandNo);
		
		if (modelCount > 0) {
			return false; // 브랜드가 모델에 등록되어 있어 삭제 불가
		}
		
		// 2. 등록된 모델이 없으면 삭제
		int delCount = adminProductInfoMapper.removeBrandInfoByNo(brandNo);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeCategoryInfoByNo(String categoryNo) {
		
		// 1. 해당 카테고리가 등록된 품목 또는 카테고리스펙이 있는지 확인
		int itemCount = adminProductInfoMapper.countItemsByCategoryNo(categoryNo);
		int specCount = adminProductInfoMapper.countSpecsByCategoryNo(categoryNo);
		
		if (itemCount > 0 || specCount > 0) {
			return false; // 카테고리가 품목 또는 카테고리스펙에 등록되어 있어 삭제 불가
		}
		
		// 2. 아무것도 등록되어 있지 않다면 삭제 
		int delCount = adminProductInfoMapper.removeCategoryInfoByNo(categoryNo);
		
		boolean isDel = delCount > 0 ? true : false; 
		
		return isDel;
	}
	
	// 상품정보 중복체크
	@Override
	public boolean isSpecContentCheck(String modelSpecName, String modelNo, String specNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("modelSpecName", modelSpecName);
		params.put("modelNo", modelNo);
		params.put("specNo", specNo);
		
		return adminProductInfoMapper.isSpecContentCheck(params);
	}
	
	@Override
	public boolean isSpecNameCheck(String specName, String categoryNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("specName", specName);
		params.put("categoryNo", categoryNo);
		
		return adminProductInfoMapper.isSpecNameCheck(params);
	}
	@Override
	public boolean isBenefitNameCheck(String benefitName) {
		
		return adminProductInfoMapper.isBenefitNameCheck(benefitName);
	}
	
	@Override
	public boolean isModelNameCheck(String categoryNo, String brandNo, String itemNo, String modelName) {
		Map<String, Object> params = new HashMap<>();
		params.put("modelName", modelName);
		params.put("categoryNo", categoryNo);
		params.put("brandNo", brandNo);
		params.put("itemNo", itemNo);
		
		return adminProductInfoMapper.isModelNameCheck(params);
	}
	
	@Override
	public boolean isItemNameCheck(String itemName, String categoryNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("itemName", itemName);
		params.put("categoryNo", categoryNo);
		
		return adminProductInfoMapper.isItemNameCheck(params);
	}
	
	@Override
	public boolean isBrandNameCheck(String brandName) {
		
		return adminProductInfoMapper.isBrandNameCheck(brandName);
	}
	
	@Override
	public boolean isCategoryCheck(String lgCategory, String mdCategory, String smCategory) {
		Map<String, Object> params = new HashMap<>();
		params.put("lgCategory", lgCategory);
		params.put("mdCategory", mdCategory);
		params.put("smCategory", smCategory);
		
		return adminProductInfoMapper.isCategoryCheck(params);
	}
	
	// 상품정보 수정
	
	@Override
	public void modifyModelSpec(ProductInfoModelSpec productInfoModelSpec) {
		
		adminProductInfoMapper.modifyModelSpec(productInfoModelSpec);
	}
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
	
	/**
	 *  상품정보 조회
	 */
	// 모델코드로 카테고리코드 조회 (모델별/상세스펙 등록)
	@Override
	public String getCategoryNoByModelNo(String modelNo) {
		
		return adminProductInfoMapper.getCategoryNoByModelNo(modelNo);
	}
	
	@Override
	public ProductInfoModelSpec getModelSpecInfoByNo(String modelSpecNo) {
	
		return adminProductInfoMapper.getModelSpecInfoByNo(modelSpecNo);
	}
	
	@Override
	public ProductInfoCategorySpec getCategorySpecInfoByNo(String categorySpecNo) {
		
		return adminProductInfoMapper.getCategorySpecInfoByNo(categorySpecNo);
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
	public void addModelSpec(ProductInfoModelSpec productInfoModelSpec) {
		log.info("상품등록 전 : {}", productInfoModelSpec);
		
		adminProductInfoMapper.addModelSpec(productInfoModelSpec);
		
		log.info("상품등록 후 : {}", productInfoModelSpec);	
	}
	
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
	
	/**
	 *  상품정보 목록 조회
	 */
	// 등록되어있는 카테고리 중분류 목록 조회(카테고리 등록)	
	@Override
	public List<ProductInfoCategory> getMdCategory() {
		
		return adminProductInfoMapper.getMdCategory();
	}
	// 등록되어있는 카테고리 대분류 목록 조회(카테고리 등록)	
	@Override
	public List<ProductInfoCategory> getLgCategory() {
		
		return adminProductInfoMapper.getLgCategory();
	}
	
	// 카테고리코드로 스펙 목록 조회 (모델별/상세스펙 등록)
	@Override
	public List<ProductInfoCategorySpec> getSpecListByCategoryNo(String categoryNo) {
		
		return adminProductInfoMapper.getSpecListByCategoryNo(categoryNo);
	}
	
	// 카테고리코드로 품목 목록 조회 (모델 등록)
	@Override 	
	public List<ProductInfoItem> getItemListByCategoryNo(String categoryNo) {
		
		return adminProductInfoMapper.getItemListByCategoryNo(categoryNo);
	}
	
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
