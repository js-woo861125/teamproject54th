package ks54team01.enterprise.paymentCalc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;

@Mapper
public interface PaymentCalcMapper {
	
	
	List<AdminFee> getEnterPricePayCalc(@Param("entCeoNo") String ceoCode, @Param("settlementMonth") String settlementMonth);
	
	List<AdminMonthlyFee> getEntMonthlyCalc(@Param("entCeoNo") String ceoCode, @Param("settlementMonth") String settlementMonth);
	
	
}
