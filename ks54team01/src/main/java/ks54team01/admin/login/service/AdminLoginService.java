package ks54team01.admin.login.service;

import java.util.Map;

public interface AdminLoginService {
	// 회원정보 조회
	Map<String, Object> matchMember(String memberId, String memberPw);
}
