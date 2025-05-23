package ks54team01.customer.contract.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.contract.domain.CustomerContract;

@Mapper
public interface CustomerContractMapper {

	
	int addCustomerContract(CustomerContract customerContract);
}
