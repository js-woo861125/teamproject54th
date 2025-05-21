package ks54team01.enterprise.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;

@Mapper
public interface EnterpriseMarginRatioMapper {

	int addEnterpriseMarginRatio(EnterpriseMarginRatio enterpriseMarginRatio);
	
	// 업체별 마진율 사용유무 설정 (사실상 삭제 기능도 포함)
	int modifyMarginRatioUseStatus(String marginRatioNum, String useStatus);
	
	// 업체별 설정한 마진율 변경
	void modifyEnterpriseMarginRatio(EnterpriseMarginRatio marginRatio);
	
	// 업체별 마진율 설정
	List<EnterpriseMarginRatio> getEnterpriseMarginRatio();
}
