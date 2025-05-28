package ks54team01.customer.contract.service;

import ks54team01.customer.contract.domain.CustomerContract;

public interface CustomerContractService {

	
	void modifyRentalContractStatus(String rentalContractNo);
	
	void addCustomerContract(CustomerContract customerContract);
}
