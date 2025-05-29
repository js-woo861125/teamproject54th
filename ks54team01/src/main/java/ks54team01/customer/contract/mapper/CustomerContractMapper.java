package ks54team01.customer.contract.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.contract.domain.CustomerContract;

@Mapper
public interface CustomerContractMapper {

	//고객 계약리스트 조회
	List<CustomerContract> myCustomerContractList(String custId, String searchKey);
	
	void modifyRentalContractStatus(String rentalContractNo);
	
	int addCustomerContract(CustomerContract customerContract);
}
