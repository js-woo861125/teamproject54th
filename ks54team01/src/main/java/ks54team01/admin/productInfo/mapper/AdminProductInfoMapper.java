package ks54team01.admin.productInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

@Mapper
public interface AdminProductInfoMapper {
	
	// 상품정보 카테고리 등록
	int addCategory(ProductInfoCategory productInfoCategory);
	
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
