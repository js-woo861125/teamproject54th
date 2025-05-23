package ks54team01.admin.manage.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.manage.domain.Admin;

@Mapper
public interface AdminManageMapper {
	// 관리자정보 수정
	void modifyAdminInfo(Admin admin);
	
	// 공통정보 수정
	void modifyCommonInfo(Admin admin);
	
	// 관리자 정보 조회(로그인)
	Admin findAdminById(String memberId);

}
