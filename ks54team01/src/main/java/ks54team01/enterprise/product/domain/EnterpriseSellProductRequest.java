package ks54team01.enterprise.product.domain;

import java.util.List;

import lombok.Data;

@Data
public class EnterpriseSellProductRequest {
    private EnterpriseProduct enterpriseProduct;
    private List<String> benefitNoList;
    private List<String> benefitDetailList;
    private Double penaltyFeeRatio;
    private Integer periodStart;
    private Integer periodEnd;   

}
