package ks54team01.enterprise.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;

@Mapper
public interface EnterpriseProductBenefitMapper {	
	  
	void insertEnterpriseProductBenefit(EnterpriseProductBenefit benefit);
	  
	int deleteBenefitsBySellProductNo(String sellProductsNo);
	
	int modifyEnterpriseProductBenefit(EnterpriseProductBenefit benefit);
	
	List<EnterpriseProductBenefit> selectBenefitsBySellProductNo(String sellProductsNo);
}
