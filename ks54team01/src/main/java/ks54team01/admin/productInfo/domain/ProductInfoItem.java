package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoItem {

	private String itemNo;
	private String itemName;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
}
