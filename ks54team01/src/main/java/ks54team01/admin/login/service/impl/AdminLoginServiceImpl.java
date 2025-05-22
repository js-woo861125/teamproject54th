package ks54team01.admin.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.login.service.AdminLoginService;
import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.member.domain.CommonMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminLoginServiceImpl implements AdminLoginService{
	
	private final LoginMapper loginMapper;
	
	 /**
     * 회원 정보(로그인시)
     */
	@Override
    public Map<String, Object> matchMember(String memberId, String memberPw) {
    	
    	boolean isMatched = false;
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	CommonMember memberInfo = loginMapper.getMemberInfoById(memberId);
    	
    	if(memberInfo != null) {
    		String checkPw = memberInfo.getMemberPw();
    		if(checkPw.equals(memberPw)) {
    			isMatched = true;
    			resultMap.put("memberInfo", memberInfo);
    		}
    	}
    	
    	resultMap.put("isMatched", isMatched);
    	
    	return resultMap;
    }
	
}

