package ks54team01.admin.productInfo.service;

import java.util.List;
import java.util.Map;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.domain.ProductInfoModelSpec;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;

public interface AdminProductInfoService {
	
	/**
	 * 사용유무 상태 변경
	 */
	// 모델별/상세스펙 상태 변경
	void updateModelSpecUseStatus(String modelSpecNo, String useStatus);
	// 카테고리별/상세스펙 상태 변경
	void updateCategorySpecUseStatus(String specNo, String useStatus);
	// 전체혜택 상태 변경
	void updateBenefitUseStatus(String benefitNo, String useStatus);		
	// 모델 상태 변경	
	void updateModelUseStatus(String modelNo, String useStatus);
	// 품목 상태 변경
	void updateItemUseStatus(String itemNo, String useStatus);		
	// 브랜드 상태 변경
	void updateBrandUseStatus(String brandNo, String useStatus);	
	// 카테고리 상태 변경
	void updateCategoryUseStatus(String categoryNo, String useStatus);	
	
	/**
	 * 상품정보 검색 
	 */
	// 모델별/상세스펙 검색
	List<ProductInfoModelSpec> getSearchModelSpec(String searchKey, String searchValue, String useStatus);
	// 카테고리별/상세스펙 검색
	List<ProductInfoCategorySpec> getSearchCategorySpec(String searchKey, String searchValue, String useStatus);
	// 전체혜택 검색
	List<ProductInfoBenefit> getSearchBenefit(String searchKey, String searchValue, String useStatus);
	// 모델 검색
	List<ProductInfoModel> getSearchModel(String searchKey, String searchValue, String useStatus);
	// 품목 검색
	List<ProductInfoItem> getSearchItem(String searchKey, String searchValue, String useStatus);	
	// 브랜드 검색 
	List<ProductInfoBrand> getSearchBrand(String searchKey, String searchValue, String useStatus, int currentPage ,int rowPerPage);
	// 카테고리 검색
	List<ProductInfoCategory> getSearchCategory(String searchKey, String searchValue, String useStatus);
	
	/**
	 * 상품정보 삭제
	 */
	// 모델별/상세스펙 삭제
	boolean removeModelSpecSpecInfoByNo(String modelSpecNo);	
	// 카테고리별/상세스펙 삭제
	boolean removeCategorySpecInfoByNo(String specNo);	
	// 전체혜택 삭제
	boolean removeBenefitInfoByNo(String benefitNo);	
	// 모델 삭제
	boolean removeModelInfoByNo(String modelNo);
	// 품목 삭제
	boolean removeItemInfoByNo(String itemNo);	
	// 브랜드 삭제
	boolean removeBrandInfoByNo(String brandNo);
	// 카테고리 삭제
	boolean removeCategoryInfoByNo(String categoryNo);
	
	/**
	 * 상품정보 중복체크
	 */
	// 모델+스펙+상세스펙내용 중복체크
	boolean isSpecContentCheck(String modelSpecName, String modelNo, String specNo);	
	// 카테고리+스펙명 중복체크
	boolean isSpecNameCheck(String specName, String categoryNo);
	// 혜택명 중복체크
	boolean isBenefitNameCheck (String benefitName);
	// 카테고리+브랜드+품목+모델명 중복체크
	boolean isModelNameCheck (String categoryNo, String brandNo, String itemNo, String modelName);	
	// 카테고리+품목명 중복체크
	boolean isItemNameCheck(String itemName, String categoryNo);
	// 브랜드명 중복체크
	boolean isBrandNameCheck (String brandName);
	// 대분류+중분류+소분류 중복체크
	boolean isCategoryCheck (String lgCategory, String mdCategory, String smCategory);	
	
	/**
	 * 상품정보 수정
	 */
	// 모델별/상세스펙 수정
	void modifyModelSpec(ProductInfoModelSpec productInfoModelSpec);
	// 카테고리별/상세스펙 수정
	void modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec);
	// 전체혜택 수정
	void modifyBenefit(ProductInfoBenefit productInfoBenefit);
	// 모델 수정
	void modifyModel(ProductInfoModel productInfoModel);
	// 품목 수정
	void modifyItem(ProductInfoItem productInfoItem);
	// 브랜드 수정
	void modifyBrand(ProductInfoBrand productInfoBrand);
	// 카테고리 수정
	void modifyCategory(ProductInfoCategory productInfoCategory);
	
	/**
	 * 상품정보 조회
	 */
	// 모델코드로 카테고리코드 조회 (모델별/상세스펙 등록)
	String getCategoryNoByModelNo(String modelNo);
	// 모델별/상세스펙 조회
	ProductInfoModelSpec getModelSpecInfoByNo(String modelSpecNo);	
	// 카테고리별/상세스펙 조회
	ProductInfoCategorySpec getCategorySpecInfoByNo(String categorySpecNo);
	// 전체혜택 조회
	ProductInfoBenefit getBenefitInfoByNo(String benefitNo);
	// 모델 조회
	ProductInfoModel getModelInfoByNo(String modelNo);
	// 품목 조회
	ProductInfoItem getItemInfoByNo(String itemNo);
	// 브랜드 조회
	ProductInfoBrand getBrandInfoByNo(String brandNo);
	// 카테고리 조회
	ProductInfoCategory getCategoryInfoByNo(String categoryNo);
	
	/**
	 * 상품정보 등록
	 */
	// 모델별/상세스펙 등록
	void addModelSpec(ProductInfoModelSpec productInfoModelSpec);
	// 카테고리별/상세스펙 등록
	void addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec);
	// 전체혜택 등록
	void addBenefit(ProductInfoBenefit productInfoBenefit);
	// 모델 등록
	void addModel(ProductInfoModel productInfoModel);
	// 품목 등록
	void addItem(ProductInfoItem productInfoItem);
	// 브랜드 등록
	void addBrand(ProductInfoBrand productInfoBrand);
	// 카테고리 등록
	void addCategory(ProductInfoCategory productInfoCategory);
	
	/**
	 * 상품정보 목록 조회
	 */
	// 등록되어있는 카테고리 중분류 목록 조회(카테고리 등록)
	List<ProductInfoCategory> getMdCategory();
	// 등록되어있는 카테고리 대분류 목록 조회(카테고리 등록)
	List<ProductInfoCategory> getLgCategory();
	// 카테고리코드로 스펙 목록 조회 (모델별/상세스펙 등록)
	List<ProductInfoCategorySpec> getSpecListByCategoryNo(String categoryNo);
	// 카테고리코드로 품목 목록 조회 (모델 등록)
	List<ProductInfoItem> getItemListByCategoryNo(String categoryNo);
	// 모델별/상세스펙 목록 조회
	List<ProductInfoModelSpec> getModelSpecList();
	// 카테고리별/상세스펙 목록 조회
	List<ProductInfoCategorySpec> getCategorySpecList();
	// 전체혜택 목록 조회
	List<ProductInfoBenefit> getBenefitList();
	// 모델 목록 조회
	List<ProductInfoModel> getModelList();
	// 품목 목록 조회
	List<ProductInfoItem> getItemList();
	// 브랜드 목록 조회
	PageInfo<ProductInfoBrand> getBrandList(Pageable pageable);
	// 카테고리 목록 조회
	List<ProductInfoCategory> getCategoryList();
}
