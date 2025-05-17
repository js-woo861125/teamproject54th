package ks54team01.admin.product.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminProductServiceImpl implements AdminProductService{
		
		private final AdminProductMapper adminProductMapper;
		private final AdminProductInfoMapper adminProductInfoMapper;
		private final FileService fileService;

		@Override
		public List<AdminProduct> getProductList() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void registerProduct(AdminAddProduct product, MultipartFile[] thumbnails, MultipartFile[] details) {
			
			// Pk 랜덤 생성
			String productNo = "Prod_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
			product.setProductNo(productNo);
			
			// model 브랜드, 스펙, 카테 가져오기
			ProductInfoModel modelInfo = adminProductInfoMapper.getModelInfoByNo(product.getModelNo());
		        product.setItemNo(modelInfo.getItemNo());
		        product.setCategoryNo(modelInfo.getCategoryNo());
		        product.setBrandNo(modelInfo.getBrandNo());
		        
		    product.setManagerId("manageid1");    
		    product.setProductStatus("비활성화");
		    LocalDateTime now = LocalDateTime.now();
	        product.setRegisterDate(now.toString());
	        product.setRevisionDate(now.toString());
		
		  //  썸네일 이미지 저장
        List<FileMetaData> thumbnailList = fileService.uploadFiles(thumbnails, "thumbnail");
        if (!thumbnailList.isEmpty()) {
            product.setProductImage(thumbnailList.get(0).getFileNewName());
        }

        //  상품 정보 DB에 등록
        adminProductMapper.insertProduct(product);

        //  파일 메타데이터 DB에 등록
        fileService.addFiles(thumbnails, "thumbnail", productNo);
        fileService.addFiles(details, "detail", productNo);
    }
}
