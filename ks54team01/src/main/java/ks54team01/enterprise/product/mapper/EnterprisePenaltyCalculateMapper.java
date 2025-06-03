package ks54team01.enterprise.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.enterprise.product.domain.EnterprisePenaltyCalculate;

@Mapper
public interface EnterprisePenaltyCalculateMapper {
	  int insertPenaltyCalculate(EnterprisePenaltyCalculate penalty);
}
