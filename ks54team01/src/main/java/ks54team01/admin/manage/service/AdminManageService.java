package ks54team01.admin.manage.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import ks54team01.admin.manage.domain.Admin;

@Service
public interface AdminManageService {
	// 관리자 정보 수정
	void modifyManagerInfo(Admin admin);
	
	// 관리자 정보 조회(로그인)
	Map<String, Object> matchAdmin(String memberId, String memberPw);
}
