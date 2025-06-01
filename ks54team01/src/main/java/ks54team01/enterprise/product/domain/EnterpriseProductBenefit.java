package ks54team01.enterprise.product.domain;

import lombok.Data;

@Data
public class EnterpriseProductBenefit {
    private String benefitNo;
    private String sellProductsNo; 
    private String entCeoNo;
    private String entEmpId;
    private String benefit;
    private String benefitDetail;
    private String registerDate;
    private String revisionDate;
    private String useStatus;  
}
