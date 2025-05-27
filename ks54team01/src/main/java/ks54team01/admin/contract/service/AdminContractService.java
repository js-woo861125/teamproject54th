package ks54team01.admin.contract.service;

import java.util.List;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.domain.AdminContractDetail;

public interface AdminContractService {
	
	List<AdminContractDetail> getContractDetail(String rentalContNo);
	
	List<AdminContract> getSearchContractList(String searchKey, String searchValue);
	
	List<AdminContract> getContractList();
}
