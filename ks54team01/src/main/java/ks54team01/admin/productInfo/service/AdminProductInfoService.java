package ks54team01.admin.productInfo.service;

import java.util.List;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.domain.ProductInfoModelSpec;

public interface AdminProductInfoService {
	
	/**
	 * 상품정보 삭제
	 */
	// 상품정보 브랜드 삭제
	boolean removeBrandInfoByNo(String brandNo);
	
	/**
	 * 상품정보 중복체크
	 */
	// 상품정보 브랜드명 중복체크
	boolean isBrandNameCheck (String brandName);
	
	/**
	 * 상품정보 수정
	 */
	// 상품정보 카테고리별/상세스펙 수정
	void modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec);
	
	// 상품정보 전체혜택 수정
	void modifyBenefit(ProductInfoBenefit productInfoBenefit);
	
	// 상품정보 모델 수정
	void modifyModel(ProductInfoModel productInfoModel);
	
	// 상품정보 품목 수정
	void modifyItem(ProductInfoItem productInfoItem);
	
	// 상품정보 브랜드 수정
	void modifyBrand(ProductInfoBrand productInfoBrand);
	
	// 상품정보 카테고리 수정
	void modifyCategory(ProductInfoCategory productInfoCategory);
	
	/**
	 * 상품정보 조회
	 */
	// 상품정보 카테고리별/상세스펙 조회
	ProductInfoCategorySpec getCategorySpecInfoByNo(String categorySpecyNo);
	
	// 상품정보 전체혜택 조회
	ProductInfoBenefit getBenefitInfoByNo(String benefitNo);
	
	// 상품정보 모델 조회
	ProductInfoModel getModelInfoByNo(String modelNo);
	
	// 상품정보 품목 조회
	ProductInfoItem getItemInfoByNo(String itemNo);
	
	// 상품정보 브랜드 조회
	ProductInfoBrand getBrandInfoByNo(String brandNo);
	
	// 상품정보 카테고리 조회
	ProductInfoCategory getCategoryInfoByNo(String categoryNo);
	
	/**
	 * 상품정보 등록
	 */
	// 상품정보 카테고리별/상세스펙 등록
	void addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec);
	
	// 상품정보 전체혜택 등록
	void addBenefit(ProductInfoBenefit productInfoBenefit);
	
	// 상품정보 모델 등록
	void addModel(ProductInfoModel productInfoModel);
	
	// 상품정보 품목 등록
	void addItem(ProductInfoItem productInfoItem);
	
	// 상품정보 브랜드 등록
	void addBrand(ProductInfoBrand productInfoBrand);
	
	// 상품정보 카테고리 등록
	void addCategory(ProductInfoCategory productInfoCategory);
	
	/**
	 * 상품정보 목록 조회
	 */
	// 상품정보 모델별/상세스펙 목록 조회
	List<ProductInfoModelSpec> getModelSpecList();
	
	// 상품정보 카테고리별/상세스펙 목록 조회
	List<ProductInfoCategorySpec> getCategorySpecList();
	
	// 상품정보 전체혜택 목록 조회
	List<ProductInfoBenefit> getBenefitList();
	
	// 상품정보 모델 목록 조회
	List<ProductInfoModel> getModelList();
	
	// 상품정보 품목 목록 조회
	List<ProductInfoItem> getItemList();
	
	// 상품정보 브랜드 목록 조회
	List<ProductInfoBrand> getBrandList();
	
	// 상품정보 카테고리 목록 조회
	List<ProductInfoCategory> getCategoryList();

}
