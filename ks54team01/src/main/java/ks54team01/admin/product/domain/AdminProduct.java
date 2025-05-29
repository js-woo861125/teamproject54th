package ks54team01.admin.product.domain;

import ks54team01.common.file.domain.FileMetaData;
import lombok.Data;

@Data
public class AdminProduct {
		private String productNo;     
	    private String categoryNo;   
	    private String brandNo;     
	    private String modelNo;     
	    private String productName;  
	    private String productStatus; 
	    private String productDetail;
	    private String registerDate; 
	    private String revisionDate; 
	    
	    private String itemNo;
	    private String productImage;
	    private String modelName;
	    private String brandName;
	    
	    private FileMetaData mainImageData; 
	}

