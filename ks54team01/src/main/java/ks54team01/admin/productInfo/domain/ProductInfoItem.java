package ks54team01.admin.productInfo.domain;

import lombok.Data;

@Data
public class ProductInfoItem {

	private String itemNo;
	private String managerId;
	private String categoryNo;
	private String itemName;
	private String itemRegDate;
	private String itemRevDate;
	private String useStatus;
	
	private ProductInfoCategory categoryInfo;
}
