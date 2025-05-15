package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoModelSpec {

	private String modelSpecNo;
	private String managerId;
	private String productNo;
	private String modelNo;
	private String specNo;
	private String modelSpecName;
	private String modelSpecRegDate;
	private String modelSpecRevDate;
	private String useStatus;
	
	private ProductInfoCategory productInfo;
	private ProductInfoBrand modelInfo;
	private ProductInfoItem specInfo;
}
