package ks54team01.admin.contract.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.domain.AdminContractDetail;
import ks54team01.admin.contract.mapper.AdminContractMapper;
import ks54team01.admin.contract.service.AdminContractService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminContractServiceImpl implements AdminContractService{

	private final AdminContractMapper adminContractMapper;
	
	@Override
	public List<AdminContractDetail> getContractDetail(String rentalContNo) {
		List<AdminContractDetail> adminContractDetail = adminContractMapper.getContractDetail(rentalContNo);
		return adminContractDetail;
	}
	
	
	@Override
	public List<AdminContract> getSearchContractList(Map<String, Object> paramMap) {
	
		// searchKey와 searchValue는 이미 paramMap에 담겨 있으므로, 별도의 switch-case 변환은 필요 없습니다.
        // Mapper XML에서 paramMap.searchKey와 paramMap.searchValue를 직접 사용합니다.

        // Mapper 인터페이스에 정의된 getSearchContractList 메서드 호출
			List<AdminContract> adminSearchContractList = adminContractMapper.getSearchContractList(paramMap);
	
		return adminSearchContractList;
	}
	
	
	@Override
	public List<AdminContract> getContractList() {

		List<AdminContract> adminContractList = adminContractMapper.getContractList();
		
		return adminContractList;
	}
}
