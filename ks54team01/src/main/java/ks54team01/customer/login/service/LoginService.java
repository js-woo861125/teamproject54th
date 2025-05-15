package ks54team01.customer.login.service;

import java.util.Map;

public interface LoginService {
	// 입점업체회원정보 일치여부 조회
	Map<String, Object> matchEntMember(String memberId, String memberPw);

	// 회원정보 일치여부 조회
	Map<String, Object> matchMember(String memberId, String memberPw);
}
