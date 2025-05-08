package ks54team01.admin.contract.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.mapper.AdminContractMapper;
import ks54team01.admin.contract.service.AdminContractService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminContractServiceImpl implements AdminContractService{

	private final AdminContractMapper adminContractMapper;
	
	
	@Override
	public List<AdminContract> getSearchContractList(String searchKey, String searchValue) {

		switch (searchKey) {
			case "custId" 	-> searchKey = "cust_id";
			case "entCeoNo" 	-> searchKey = "ent_ceo_no";		
			case "sellProdNo" 	-> searchKey = "sell_products_no";		
		}
		
		
		List<AdminContract> adminSearchContractList = adminContractMapper.getSearchContractList(searchKey, searchValue);
		
		return adminSearchContractList;
	}
	
	
	@Override
	public List<AdminContract> getContractList() {

		List<AdminContract> adminContractList = adminContractMapper.getContractList();
		
		return adminContractList;
	}
}
