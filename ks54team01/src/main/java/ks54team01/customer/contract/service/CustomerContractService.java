package ks54team01.customer.contract.service;

import java.util.List;

import ks54team01.customer.contract.domain.CustomerContract;

public interface CustomerContractService {

	
	void deleteContractByRentalContractNo(String rentalContractNo);
	
	//고객 계약리스트 조회
	List<CustomerContract> myCustomerContractList(String custId, String searchKey);
	
	void modifyRentalContractStatus(String rentalContractNo);
	
	void addCustomerContract(CustomerContract customerContract);
}
