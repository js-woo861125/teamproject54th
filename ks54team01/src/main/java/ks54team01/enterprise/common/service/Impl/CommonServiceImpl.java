package ks54team01.enterprise.common.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.enterprise.common.mapper.CommonMapper;
import ks54team01.enterprise.common.service.CommonService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

	
	private final CommonMapper commMapper;
	
	
	@Override
		public List<AdminMonthlyFee> getEntMonthlyCalc(String entCeoNo, String settlementMonth) {
			
		List<AdminMonthlyFee> getEntMonthlyCalc = commMapper.getEntMonthlyCalc(entCeoNo, settlementMonth);
		
			return getEntMonthlyCalc;
		}
	
}
