package ks54team01.enterprise.product.service;

import java.util.List;

import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;

public interface EnterpriseMarginRatioService {
	
	void addEnterpriseMarginRatio(EnterpriseMarginRatio enterpriseMarginRatio);
	
	void modifyMarginRatioUseStatus(String marginRatioNum, String useStatus);
	
	void modifyEnterpriseMarginRatio(List<EnterpriseMarginRatio> list);
	
	List<EnterpriseMarginRatio> getEnterpriseMarginRatio(String entCeoNo);
}
