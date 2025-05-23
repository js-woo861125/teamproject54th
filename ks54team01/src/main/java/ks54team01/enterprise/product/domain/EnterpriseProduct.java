package ks54team01.enterprise.product.domain;

import ks54team01.common.file.domain.FileMetaData;
import lombok.Data;

@Data
public class EnterpriseProduct {
	   private String sellProductsNo;
	   private String productName;
	   private String marginRatioNo;
	   private String entCeoNo;
	   private String entEmpId;
	   private String productsNo;
	   private int lumpSumPayPrice;
	   private int period;
	   private int calculatePrice;
	   private int finalPrice;
	   private String registerDate;
	   private String revisionDate;
	   private String useStatus;
	   private String productImage;
	   private String modelName;
	    
	    private FileMetaData mainImageData; 
}
