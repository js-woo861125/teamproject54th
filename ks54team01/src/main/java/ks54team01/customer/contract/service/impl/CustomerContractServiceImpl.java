package ks54team01.customer.contract.service.impl;

import java.util.List;
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
	
	//고객 계약리스트 조회
	@Override
	public List<CustomerContract> myCustomerContractList(String custId, String searchKey) {
		
		List<CustomerContract> myCustomerContractList = customerContractMapper.myCustomerContractList(custId, searchKey);
				
		return myCustomerContractList;
	}
	
	
	
	@Override
	public void modifyRentalContractStatus(String rentalContractNo) {

		customerContractMapper.modifyRentalContractStatus(rentalContractNo);
	}
	
	@Override
	public void addCustomerContract(CustomerContract customerContract) {
		
		String contractNo =  "rental_contract_no_" + UUID.randomUUID().toString().replace("-", "");
		customerContract.setRentalContractNo(contractNo);
	
	
		customerContractMapper.addCustomerContract(customerContract);
	}
}
