package ks54team01.enterprise.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ks54team01.admin.payment.domain.AdminMonthlyFee;

@Mapper
public interface CommonMapper {

	
	List<AdminMonthlyFee> getEntMonthlyCalc(@Param("entCeoNo") String ceoCode, @Param("settlementMonth") String settlementMonth);
	
}
