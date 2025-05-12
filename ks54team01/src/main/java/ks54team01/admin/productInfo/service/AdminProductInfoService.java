package ks54team01.admin.productInfo.service;

import java.util.List;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

public interface AdminProductInfoService {
	
	// 상품정보 카테고리 수정
	void modifyCategory(ProductInfoCategory productInfoCategory);
	
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
