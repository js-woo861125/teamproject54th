package ks54team01.admin.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

@Mapper
public interface AdminProductMapper {
	
	ProductInfoModel getModelInfoByNo(String modelNo);
	
	// 상품 등록
	int insertProduct(AdminAddProduct product);
	
	// 상품 스펙 
	List<AdminProductSpecContent> loadSpecContent(@Param("modelNo") String modelNo);
	
    List<ProductInfoCategory> loadCategoryList(); // 카테고리
    List<ProductInfoItem> loadItemList(String categoryNo); // 품목
    List<ProductInfoBrand> loadBrandList(@Param("categoryNo") String categoryNo, @Param("itemNo") String itemNo);
    List<ProductInfoModel> loadModelList(@Param("categoryNo") String categoryNo, @Param("itemNo") String itemNo, @Param("brandNo") String brandNo);
}

