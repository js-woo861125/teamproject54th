package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoModelSpec {

	private String modelSpecNo;
	private String managerId;
	private String modelNo;
	private String specNo;
	private String modelSpecName;
	private String modelSpecRegDate;
	private String modelSpecRevDate;
	private String useStatus;
	
	private ProductInfoModel modelInfo;
	private ProductInfoCategorySpec specInfo;
}
