package ks54team01.customer.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.login.service.LoginService;
import ks54team01.customer.register.domain.CustomerMember;
import ks54team01.customer.register.domain.EntMember;
import ks54team01.customer.register.mapper.RegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

	private final RegisterMapper registerMapper;
	
	@Override
	public Map<String, Object> matchEntMember(String memberId, String memberPw) {
		boolean isMatched = false;
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	EntMember memberInfo = registerMapper.getEntMemberInfoById(memberId);
    	
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
	
	
	@Override
	public Map<String, Object> matchMember(String memberId, String memberPw) {
		boolean isMatched = false;
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	CustomerMember memberInfo = registerMapper.getMemberInfoById(memberId);
    	
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
