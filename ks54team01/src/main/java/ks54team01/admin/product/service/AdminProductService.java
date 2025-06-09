package ks54team01.admin.product.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;

public interface AdminProductService {
	
	// 상품 검색 조회
	List<AdminProduct> searchProductList(String searchKey, String searchValue, String categoryNo, String status);
	
	
	// 상품 목록 조회
	public List<AdminProduct> getProductList();
	
	AdminProduct getProduct(String productNo);

	// 상품 수정
	void modifyProduct(AdminProduct product, MultipartFile[] mainImage, String deleteFileIdxs);
	
	// 상품 등록
	public void addProduct(AdminAddProduct product, MultipartFile[] mainImage);
	
	List<AdminProductSpecContent> loadSpecContent(@Param("modelNo") String modelNo);
	List<ProductInfoCategory> loadCategoryList();
	List<ProductInfoItem> loadItemList(String categoryNo);
	List<ProductInfoBrand> loadBrandList(String categoryNo, String itemNo);
	List<ProductInfoModel> loadModelList(String categoryNo, String itemNo, String brandNo);
	
	// 상품 판매중단, 되돌리기
	public void setSaleStoppage(String productNo);
	void unsetSaleStoppage(String productNo);
	
	// 상품 중복조회
	boolean isDuplicateProduct(String modelNo);
	
}

