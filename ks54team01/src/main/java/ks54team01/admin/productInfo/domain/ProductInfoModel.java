package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoModel {

	private String modelNo;
	private String modelName;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
	private ProductInfoBrand brandInfo;
	private ProductInfoItem itemInfo;
}
