package ks54team01.admin.product.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminProductServiceImpl implements AdminProductService{

		private final AdminProductMapper adminProductMapper;
		private final FileService fileService;
		
		
		@Override
	    public List<AdminProduct> searchProductList(String searchKey, String searchValue, String categoryNo, String status) {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("searchKey", searchKey);
	        paramMap.put("searchValue", searchValue);
	        paramMap.put("categoryNo", categoryNo);
	        paramMap.put("status", status);
	        return adminProductMapper.searchProductList(paramMap);
	    }
		
		@Override
		public boolean isDuplicateProduct(String modelNo) {
		
			 return adminProductMapper.isDuplicateProduct(modelNo) > 0;
		}
		
		@Override
		public void modifyProduct(AdminProduct product, MultipartFile[] mainImage, MultipartFile[] thumbnails, String deleteFileIdxs) {
		 
		    adminProductMapper.modifyProduct(product);


		    if (deleteFileIdxs != null && !deleteFileIdxs.isEmpty()) {
		        String[] idxArr = deleteFileIdxs.split(",");
		        for (String idx : idxArr) {
		            fileService.deleteFiles(idx); // 실제 row 삭제
		        }
		    }


		    if (mainImage != null && mainImage.length > 0 && !mainImage[0].isEmpty()) {
		        fileService.addFiles(mainImage, "mainImage", product.getModelNo());
		    }


		    if (thumbnails != null && thumbnails.length > 0 && !thumbnails[0].isEmpty()) {
		        fileService.addFiles(thumbnails, "thumbnail", product.getModelNo());
		    }
		}
		
		@Override
		public AdminProduct getProduct(String productNo) {
			
		    return adminProductMapper.getProduct(productNo); 
		}
		
		@Override
		public List<AdminProduct> getProductList() {
		
			return adminProductMapper.getProductList();
		}
		
		@Override
	    public List<ProductInfoCategory> loadCategoryList() {
	        return adminProductMapper.loadCategoryList();
	    }

		public List<ProductInfoItem> loadItemList(String categoryNo) {
		    return adminProductMapper.loadItemList(categoryNo);
		}

		@Override
		public List<ProductInfoBrand> loadBrandList(String categoryNo, String itemNo) {
		    return adminProductMapper.loadBrandList(categoryNo, itemNo);
		}

	    @Override
	    public List<ProductInfoModel> loadModelList(String categoryNo, String itemNo, String brandNo) {
	        return adminProductMapper.loadModelList(categoryNo, itemNo, brandNo);
	    }
		
		@Override
		public List<AdminProductSpecContent> loadSpecContent(String modelNo) {
	
			return adminProductMapper.loadSpecContent(modelNo);
		}
		
		@Override
		public void addProduct(AdminAddProduct product, MultipartFile[] mainImage, MultipartFile[] thumbnails) {
			
			// Pk 랜덤 생성
			String productNo = UUID.randomUUID().toString().replace("-", "");
			product.setProductNo(productNo);
			
			// model 브랜드, 스펙, 카테 가져오기
			ProductInfoModel modelInfo = adminProductMapper.getModelInfoByNo(product.getModelNo());
	        product.setItemNo(modelInfo.getItemNo());
	        product.setCategoryNo(modelInfo.getCategoryNo());
	        product.setBrandNo(modelInfo.getBrandNo());
		        
		    product.setManagerId("managerid1");    
		    product.setProductStatus("비활성화");
		    LocalDateTime now = LocalDateTime.now();
	        product.setRegisterDate(now.toString());
	        product.setRevisionDate(now.toString());
		
	 
	        //  상품 정보 DB에 등록
	        adminProductMapper.insertProduct(product);
	
	        //  파일 메타데이터 DB에 등록   
	        fileService.addFiles(mainImage, "mainImage", product.getModelNo());
	        fileService.addFiles(thumbnails, "thumbnail", product.getModelNo());
		}
		
		
		@Override
		public void setSaleStoppage(String productNo) {
		 adminProductMapper.setSaleStoppage(productNo);;
		}
		@Override
		public void unsetSaleStoppage(String productNo) {
		 adminProductMapper.unsetSaleStoppage(productNo);
		
		}
}
