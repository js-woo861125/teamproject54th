package ks54team01.admin.enterprise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.enterprise.domain.AdminEntDetail;
import ks54team01.admin.enterprise.domain.AdminEntList;

@Mapper
public interface AdminEntMapper {
	// 거래처 목록 조회
	List<AdminEntList> getEntList();
	
	// 입점업체 상세 조회
	AdminEntDetail getEntDetail(String ceoCode);
	
}
