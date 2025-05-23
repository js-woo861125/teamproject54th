package ks54team01.customer.contract.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.contract.domain.CustomerContract;
import ks54team01.customer.contract.mapper.CustomerContractMapper;
import ks54team01.customer.contract.service.CustomerContractService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerContractServiceImpl implements CustomerContractService {

	private final CustomerContractMapper customerContractMapper;
	
	@Override
	public void addCustomerContract(CustomerContract customerContract) {
		
		String contractNo =  "contractNo_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		customerContract.setRentalContractNo(contractNo);
	
	
		customerContractMapper.addCustomerContract(customerContract);
	}
}
