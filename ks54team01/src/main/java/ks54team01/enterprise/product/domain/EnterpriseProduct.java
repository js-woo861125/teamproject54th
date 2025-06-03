package ks54team01.enterprise.product.domain;

import java.time.LocalDateTime;

import ks54team01.common.file.domain.FileMetaData;
import lombok.Data;

@Data
public class EnterpriseProduct {
	   private String sellProductsNo;
	   private String productName;
	   private String marginRatioNum;
	   private String entCeoNo;
	   private String entEmpId;
	   private String productsNo;
	   private int lumpSumPayPrice;
	   private int period;
	   private int calculatePrice;
	   private int finalPrice;
	   private LocalDateTime registerDate;
	   private LocalDateTime revisionDate;
	   private String useStatus;
	   private String productImage;
	   private String modelName;
	    
	   private FileMetaData mainImageData;
	   

}
