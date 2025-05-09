package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoCategorySpec {

	private String specNo;
	private String specName;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
}
