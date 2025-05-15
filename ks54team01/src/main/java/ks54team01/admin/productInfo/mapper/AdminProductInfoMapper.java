package ks54team01.admin.productInfo.mapper;

import java.util.List;

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
	 * 상품정보 삭제
	 */
	// 상품정보 브랜드 삭제
	int removeBrandInfoByNo(String brandNo);
	
	/**
	 * 상품정보 중복체크
	 */
	// 상품정보 브랜드명 중복체크
	boolean isBrandNameCheck (String brandName);
	
	/**
	 * 상품정보 수정
	 */
	// 상품정보 카테고리별/상세스펙 수정
	int modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec);
	
	// 상품정보 전체혜택 수정
	int modifyBenefit(ProductInfoBenefit productInfoBenefit);
	
	// 상품정보 모델 수정
	int modifyModel(ProductInfoModel productInfoModel);
	
	// 상품정보 품목 수정
	int modifyItem(ProductInfoItem productInfoItem);
	
	// 상품정보 브랜드 수정
	int modifyBrand(ProductInfoBrand productInfoBrand);
	
	// 상품정보 카테고리 수정
	int modifyCategory(ProductInfoCategory productInfoCategory);
	
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
	int addCategorySpec(ProductInfoCategorySpec ProductInfoCategorySpec);
	
	// 상품정보 전체혜택 등록
	int addBenefit(ProductInfoBenefit productInfoBenefit);
	
	// 상품정보 모델 등록
	int addModel(ProductInfoModel productInfoModel);
	
	// 상품정보 품목 등록
	int addItem(ProductInfoItem productInfoItem);
	
	// 상품정보 브랜드 등록
	int addBrand(ProductInfoBrand productInfoBrand);
	
	// 상품정보 카테고리 등록
	int addCategory(ProductInfoCategory productInfoCategory);
	
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
