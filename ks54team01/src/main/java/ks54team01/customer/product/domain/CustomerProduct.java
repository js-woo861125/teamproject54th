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
		private String productsImage;
		private String registerDate;
		private String revisionDate;
		private String lumpPrice;
		private String minRentalPrice;
		
		private List<CustomerSellProduct> customerSellProduct;
		private ProductCate productCate;
		private List<EntCeo> entCeo;
		

	}
