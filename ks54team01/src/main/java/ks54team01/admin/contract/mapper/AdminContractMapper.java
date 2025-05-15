package ks54team01.admin.contract.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.domain.AdminContractDetail;

@Mapper
public interface AdminContractMapper {
	
	//계약 상세
	List<AdminContractDetail> getContractDetail(String rentalContNo);
	
	// 업체&고객 렌탈계약정보 검색
	List<AdminContract> getSearchContractList(String searchKey, String searchValue);
	
	// 업체&고객 렌탈계약정보 조회
	List<AdminContract> getContractList();

}
