package ks54team01.enterprise.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

@Mapper
public interface EnterpriseProductQuantityMapper {
	  void insertEnterpriseProductQuantity(EnterpriseProductQuantity quantity);
}
