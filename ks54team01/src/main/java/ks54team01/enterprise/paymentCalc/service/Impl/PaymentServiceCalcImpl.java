package ks54team01.enterprise.paymentCalc.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.enterprise.paymentCalc.mapper.PaymentCalcMapper;
import ks54team01.enterprise.paymentCalc.service.PaymentServiceCalc;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceCalcImpl implements PaymentServiceCalc{

	private final PaymentCalcMapper paymentCalcMapper;
	
	@Override
		public List<AdminFee> getEnterPricePayCalc(String ceoCode, String settlementMonth) {
			
	
			List<AdminFee> getEnterPricePayCalc = paymentCalcMapper.getEnterPricePayCalc(ceoCode, settlementMonth);
		
		return getEnterPricePayCalc;
		}
	
	
	@Override
	public List<AdminMonthlyFee> getEntMonthlyCalc(String ceoCode, String settlementMonth) {
		
		List<AdminMonthlyFee> getEntMonthlyCalc = paymentCalcMapper.getEntMonthlyCalc(ceoCode, settlementMonth);
		
	return getEntMonthlyCalc;
	}
	
}
