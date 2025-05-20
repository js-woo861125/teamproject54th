package ks54team01.enterprise.product.domain;

import lombok.Data;

@Data
public class EnterpriseMarginRatio {

	private String marginRatioNum;
	private String EnterpriseCeoNum;
	private String EnterpriseEmployeeId;
	private int period;
	private double marginRatio;
	private String registerDate;
	private String revisionDate;
	private String useStatus;
}
