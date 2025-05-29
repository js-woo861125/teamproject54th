package ks54team01.admin.contract.service;

import java.util.List;
import java.util.Map;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.domain.AdminContractDetail;

public interface AdminContractService {
	
	List<AdminContractDetail> getContractDetail(String rentalContNo);
	
	List<AdminContract> getSearchContractList(Map<String, Object> paramMap);
	
	List<AdminContract> getContractList();
}
