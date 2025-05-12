package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoModel {

	private String modelNo;
	private String managerId;
	private String brandNo;
	private String itemNo;
	private String categoryNo;
	private String modelName;
	private String modelRegDate;
	private String modelRevDate;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
	private ProductInfoBrand brandInfo;
	private ProductInfoItem itemInfo;
}
