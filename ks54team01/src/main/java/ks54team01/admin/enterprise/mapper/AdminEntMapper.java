package ks54team01.admin.enterprise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.enterprise.domain.AdminEntList;

@Mapper
public interface AdminEntMapper {
	// 거래처 목록 조회
	List<AdminEntList> getEntList();
	
	//
	
}
