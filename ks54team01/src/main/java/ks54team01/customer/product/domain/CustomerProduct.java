package ks54team01.customer.product.domain;

import java.util.List;

import lombok.Data;

	@Data
	public class CustomerProduct {

		private String productsNum;
		private String managerId;
		private String modelNum;
		private String itemNum;
		private String productCategoryNum;
		private String productsName;
		private String productsStatus;
//		private String productsImage;
		private String registerDate;
		private String revisionDate;
		private String lumpPrice;
		private String minRentalPrice;
		private String maxPeriod;
		
		private String imageFilePath;
	
		
		
		// files 테이블에서 가져올 이미지 관련 필드 추가
	    private String imageFileName; // f.file_new_name 또는 f.file_path 에 매핑
	    private String imageType; // f.file_type 에 매핑 (예: "image/jpeg", "image/png")
		
		
		private List<CustomerSellProduct> customerSellProduct;
		private ProductCate productCate;
		private List<EntCeo> entCeo;
		
		private CustomerModel customerModel;
		private CustomerModelSpecContent customerModelSpecContent;
		private CustomerProductSpec customerProductSpec;
		private CustomerBenefit customerBenefit;
		private CustomerProductBenefit customerProductBenefit;

	}
