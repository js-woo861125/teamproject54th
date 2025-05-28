package ks54team01.admin.manage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.manage.domain.Admin;
import ks54team01.admin.manage.mapper.AdminManageMapper;
import ks54team01.admin.manage.service.AdminManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminManageServiceImpl implements AdminManageService{

	private final AdminManageMapper adminManageMapper;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 관리자 정보 수정
	 */
	@Override
	public void modifyManagerInfo(Admin admin) {
		
		adminManageMapper.modifyCommonInfo(admin);
		adminManageMapper.modifyAdminInfo(admin);
		
	}
	
	/**
	 * 관리자 정보 조회(로그인)
	 */
	@Override
	public Map<String, Object> matchAdmin(String memberId, String memberPw) {
	    Map<String, Object> resultMap = new HashMap<>();

	    Admin adminInfo = adminManageMapper.findAdminById(memberId);
	    
	    String adminName = adminInfo.getManagerName();
	    String memberType = adminInfo.getMemberType();
	    String encodedPw = adminInfo.getMemberPw(); 
	    
	    if (!passwordEncoder.matches(memberPw, encodedPw)) {
			log.warn("로그인 실패: 비밀번호 불일치"); return null; 
		}
	    
	
	    if (adminInfo != null) {
           log.info("관리자 로그인 성공");
           resultMap.put("adminInfo", adminInfo);
           resultMap.put("adminName", adminName);
           resultMap.put("memberType", memberType);
           
           return resultMap;
       }

	    log.warn("로그인 실패: 비밀번호 불일치 또는 정보 없음");
       return null;
	}
}
