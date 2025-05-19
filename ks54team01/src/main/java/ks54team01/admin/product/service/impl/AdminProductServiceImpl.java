package ks54team01.admin.product.service.impl;

import java.time.LocalDateTime;
import java.util.List;
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
import ks54team01.common.file.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminProductServiceImpl implements AdminProductService{

		private final FileServiceImpl fileServiceImpl;	
		private final AdminProductMapper adminProductMapper;
		private final FileService fileService;

	
		@Override
		public List<AdminProduct> getProductList() {
			// TODO Auto-generated method stub
			return null;
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
		public void addProduct(AdminAddProduct product, MultipartFile[] mainImage, MultipartFile[] thumbnails, MultipartFile[] details) {
			
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
		
	        //  썸네일 이미지 저장
	        //List<FileMetaData> thumbnailList = fileService.uploadFiles(thumbnails, "thumbnail");
	
	        //  상품 정보 DB에 등록
	        adminProductMapper.insertProduct(product);
	
	        //  파일 메타데이터 DB에 등록   
	        fileService.addFiles(mainImage, "main", product.getModelNo());
	        fileService.addFiles(thumbnails, "thumbnail", product.getModelNo());
	        fileService.addFiles(details, "detail", product.getModelNo());
        
 
		}
}
