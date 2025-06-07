package ks54team01.admin.productInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.domain.ProductInfoModelSpec;

@Mapper
public interface AdminProductInfoMapper {

	/**
	 * 상품정보 사용유무 필터링
	 */
	// 카테고리별/상세스펙 사용유무 필터링
	List<ProductInfoCategorySpec> getUsableCateogrySpecList();
	// 모델 사용유무 필터링
	List<ProductInfoModel> getUsableModelList();
	// 품목 사용유무 필터링
	List<ProductInfoItem> getUsableItemList();
	// 브랜드 사용유무 필터링
	List<ProductInfoBrand> getUsableBrandList();
	// 카테고리 사용유무 필터링
	List<ProductInfoCategory> getUsableCategoryList();
	
	/**
	 * 상품정보 등록 조회
	 */
	// 카테고리별/상세스펙코드로 등록된 모델별/상세스펙 조회 (카테고리별/상세스펙 삭제)
	int countModelSpecsBySpecNo(String specNo);
	// 혜택코드로 등록된 입점업체 혜택상세 조회 (혜택 삭제)
	int countBenefitDetailsByBenefitNo (String benefitNo);
	// 모델코드로 등록된 모델별/상세스펙 조회 (모델 삭제)
	int countModelSpecsByModelNo (String modelNo);
	// 품목코드로 등록된 모델 조회 (품목 삭제)
	int countModelsByItemNo (String itemNo);
	// 브랜드코드로 등록된 모델 조회 (브랜드 삭제)
	int countModelsByBrandNo(String brandNo);
	// 카테고리코드로 등록된 카테고리별/상세스펙 조회 (카테고리 삭제)
	int countSpecsByCategoryNo(String categoryNo);
	// 카테고리코드로 등록된 품목 조회 (카테고리 삭제)
	int countItemsByCategoryNo(String categoryNo);
	
	/**
	 * 사용유무 상태 변경
	 */
	// 모델별/상세스펙 상태 변경
	int updateModelSpecUseStatus(String modelSpecNo, String useStatus);
	// 카테고리별/상세스펙 상태 변경
	int updateCategorySpecUseStatus(String specNo, String useStatus);
	// 전체혜택 상태 변경
	int updateBenefitUseStatus(String benefitNo, String useStatus);	
	// 모델 상태 변경
	int updateModelUseStatus(String modelNo, String useStatus);
	// 품목 상태 변경
	int updateItemUseStatus(String itemNo, String useStatus);	
	// 브랜드 상태 변경
	int updateBrandUseStatus(String brandNo, String useStatus);
	// 카테고리 상태 변경
	int updateCategoryUseStatus(String categoryNo, String useStatus);
	
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
	List<ProductInfoBrand> getSearchBrand(String searchKey, String searchValue, String useStatus);
	// 카테고리 검색
	List<ProductInfoCategory> getSearchCategory(String searchKey, String searchValue, String useStatus);
	
	/**
	 * 상품정보 삭제
	 */
	// 모델별/상세스펙 삭제
	int removeModelSpecSpecInfoByNo(String modelSpecNo);
	// 카테고리별/상세스펙 삭제
	int removeCategorySpecInfoByNo(String specNo);
	// 전체혜택 삭제
	int removeBenefitInfoByNo(String benefitNo);
	// 모델 삭제
	int removeModelInfoByNo(String modelNo);
	// 품목 삭제
	int removeItemInfoByNo(String itemNo);
	// 브랜드 삭제
	int removeBrandInfoByNo(String brandNo);
	// 카테고리 삭제
	int removeCategoryInfoByNo(String categoryNo);
	
	/**
	 * 상품정보 중복체크
	 */
	// 모델별/상세스펙(모델 + 스펙 + 상세스펙내용) 중복체크
	boolean isSpecContentCheck(Map<String, Object> params);
	// 카테고리별/상세스펙(카테고리 + 스펙명) 중복체크
	boolean isSpecNameCheck(Map<String, Object> params);
	// 전체혜택(혜택명) 중복체크
	boolean isBenefitNameCheck (String benefitName);
	// 모델(카테고리 + 브랜드 + 품목 + 모델명) 중복체크
	boolean isModelNameCheck (Map<String, Object> params);
	// 품목(카테고리 + 품목명) 중복체크
	boolean isItemNameCheck(Map<String, Object> params);
	// 브랜드(브랜드명) 중복체크
	boolean isBrandNameCheck (String brandName);
	// 카테고리(대분류+중분류+소분류) 중복체크
	boolean isCategoryCheck (Map<String, Object> params);
	
	/**
	 * 상품정보 수정
	 */
	// 모델별/상세스펙 수정
	int modifyModelSpec(ProductInfoModelSpec productInfoModelSpec);
	// 카테고리별/상세스펙 수정
	int modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec);
	// 전체혜택 수정
	int modifyBenefit(ProductInfoBenefit productInfoBenefit);
	// 모델 수정
	int modifyModel(ProductInfoModel productInfoModel);
	// 품목 수정
	int modifyItem(ProductInfoItem productInfoItem);
	// 브랜드 수정
	int modifyBrand(ProductInfoBrand productInfoBrand);
	// 카테고리 수정
	int modifyCategory(ProductInfoCategory productInfoCategory);
	
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
	int addModelSpec(ProductInfoModelSpec productInfoModelSpec);
	// 카테고리별/상세스펙 등록
	int addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec);
	// 전체혜택 등록
	int addBenefit(ProductInfoBenefit productInfoBenefit);
	// 모델 등록
	int addModel(ProductInfoModel productInfoModel);
	// 품목 등록
	int addItem(ProductInfoItem productInfoItem);
	// 브랜드 등록
	int addBrand(ProductInfoBrand productInfoBrand);
	// 카테고리 등록
	int addCategory(ProductInfoCategory productInfoCategory);
	

	/**
	 * 상품정보 목록 조회
	 */
	// 카테고리코드로 스펙 목록 조회 (모델별/상세스펙 등록)
	List<ProductInfoCategorySpec> getSpecListByCategoryNo(String categoryNo);
	// 카테고리코드로 품목 목록 조회 (모델 등록)
	List<ProductInfoItem> getItemListByCategoryNo(String categoryNo);
	// 카테고리 대분류로 중분류 목록 조회 (품목 등록)
//	List<ProductInfoCategory> getMdCategoryByLgCategory(String lgCategoryNo);
	// 등록되어있는 카테고리 중분류 목록 조회 (카테고리 등록)
	List<ProductInfoCategory> getMdCategory();
	// 등록되어있는 카테고리 대분류 목록 조회 (카테고리 등록)
	List<ProductInfoCategory> getLgCategory();
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
	List<ProductInfoBrand> getBrandList();
	// 카테고리 목록 조회
	List<ProductInfoCategory> getCategoryList();
}
