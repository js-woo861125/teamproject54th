package ks54team01.admin.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

@Mapper
public interface AdminProductMapper {
	// 상품 상태 변경 시 해당 상품정보 상태 변경
	void updateProductStatus(@Param("productNo") Long productNo, @Param("productStatus") String productStatus);
	
	// 상품 수정
	int modifyProduct(AdminProduct product);
	
	// 플랫폼 상품 조회
	List<AdminProduct>getProductList();
	
	// 상품 중복 체크
	int isDuplicateProduct(String modelNo);
	
	// 상품 조회
	AdminProduct getProduct(@Param("productNo") String productNo);
	
	ProductInfoModel getModelInfoByNo(@Param("modelNo") String modelNo);
	
	// 상품 등록
	int insertProduct(AdminAddProduct product);
	
	// 상품 스펙 
	List<AdminProductSpecContent> loadSpecContent(@Param("modelNo") String modelNo);
	
    List<ProductInfoCategory> loadCategoryList(); // 카테고리
    List<ProductInfoItem> loadItemList(String categoryNo); // 품목
    List<ProductInfoBrand> loadBrandList(@Param("categoryNo") String categoryNo, @Param("itemNo") String itemNo);
    List<ProductInfoModel> loadModelList(@Param("categoryNo") String categoryNo, @Param("itemNo") String itemNo, @Param("brandNo") String brandNo);
    
    void setSaleStoppage(@Param("productNo")String productNo);
    
    void unsetSaleStoppage(@Param("productNo") String productNo);
    
    // 상품 검색
    List<AdminProduct> searchProductList(Map<String, Object> paramMap);
}

