package ks54team01.admin.enterprise.service;

import java.util.List;

import ks54team01.admin.enterprise.domain.AdminEntDetail;
import ks54team01.admin.enterprise.domain.AdminEntList;

public interface AdminEntListService {
	
	
	
	// 거래처 목록 조회
		List<AdminEntList> getEntList();
		
	// 거래처 상세 조회
		AdminEntDetail getEntDetail(String ceoCode);
}
