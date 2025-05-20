package ks54team01.admin.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.payment.domain.AdminMonthlyFee;

@Mapper
public interface AdminPaymentCalcMapper {
	
	List<AdminMonthlyFee> getAdminPaymentCalc(String ceoNo);
}
