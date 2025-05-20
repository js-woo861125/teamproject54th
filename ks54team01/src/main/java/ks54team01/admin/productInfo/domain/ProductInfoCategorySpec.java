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
	
    public String getSpecNo() {
        return specNo;
    }

    public void setSpecNo(String specNo) {
        this.specNo = specNo;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
	
	private ProductInfoCategory categoryInfo;
}
