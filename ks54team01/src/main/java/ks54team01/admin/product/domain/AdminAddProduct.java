package ks54team01.admin.product.domain;

import lombok.Data;

@Data
public class AdminAddProduct {

    private String productNo;        
    private String managerId;        
    private String modelNo;  
    private String brandNo;
    private String itemNo;           
    private String categoryNo;      
    private String productName;      
    private String productStatus;   
    private String productImage;    
    private String registerDate;     
    private String revisionDate;     
}
