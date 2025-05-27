package ks54team01.admin.enterprise.service;

import java.util.List;

import ks54team01.admin.enterprise.domain.AdminEntAddContract;
import ks54team01.admin.enterprise.domain.AdminEntDetail;
import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.customer.member.domain.EntMember;

public interface AdminEntListService {
	
	// 입점업체 계약 등록
		void addContract(AdminEntAddContract adminEntAddContract);
	
	// 거래처 목록 조회
		List<AdminEntList> getEntList();
		
	// 거래처 상세 조회
		AdminEntDetail getEntDetail(String ceoCode);
	
	// 거래처 검색 조회
		List<AdminEntList> getSearchEnt(String searchKey, String searchValue);
}
