package ks54team01.admin.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

public interface AdminProductService {

	// 상품 목록 조회
	List<AdminProduct> getProductList();
	
	// 상품 등록
	void registerProduct(AdminAddProduct product, MultipartFile[] thumbnails, MultipartFile[] details);
	
	/*
	 * List<ProductInfoItem> getItemListByCategory(String categoryNo);
	 * List<ProductInfoBrand> getBrandListByItem(String categoryNo, String itemNo);
	 * List<ProductInfoModel> getModelListByCondition(String categoryNo, String
	 * itemNo, String brandNo);
	 */
	
}
