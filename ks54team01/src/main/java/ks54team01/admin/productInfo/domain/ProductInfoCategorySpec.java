package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoCategorySpec {

	private String specNo;
	private String managerId;
	private String categoryNo;
	private String specName;
	private String specRegDate;
	private String specRevDate;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
}
