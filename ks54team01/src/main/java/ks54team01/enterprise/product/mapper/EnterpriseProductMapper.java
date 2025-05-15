package ks54team01.enterprise.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

@Mapper
public interface EnterpriseProductMapper {
	List<EnterpriseProductQuantity> getQuantityList();
}
