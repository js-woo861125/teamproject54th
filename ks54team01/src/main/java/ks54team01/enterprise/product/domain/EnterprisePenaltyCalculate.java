package ks54team01.enterprise.product.domain;

import lombok.Data;

@Data
public class EnterprisePenaltyCalculate {
    private String penaltyCalculateNo;
    private String entCeoNo;
    private String entEmpId;
    private String sellProductsNo;
    private Integer periodStart;
    private Integer periodEnd;
    private Double penaltyFeeRatio;
    private String registerDate;
    private String revisionDate;
    private String useStatus;
	}
