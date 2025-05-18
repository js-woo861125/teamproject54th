package ks54team01.customer.login.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface LoginService {
	// 회원정보 조회(로그인)
	Map<String, Object> login(String memberId, String memberPw);
}
