package ks54team01.customer.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.login.service.LoginService;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
	
	private final LoginMapper loginMapper;

    @Override
    public Map<String, Object> login(String memberId, String memberPw) {
        Map<String, Object> result = new HashMap<>();

        
        String memberType = loginMapper.getMemberTypeById(memberId);
        log.info("조회된 회원 유형: {}", memberType);
        
        if (memberType == null) {
            log.warn("회원 유형 없음, 로그인 실패");
            return null;
        }
        result.put("memberType", memberType);

        switch (memberType) {
        case "개인고객":
            CustomerMember customer = loginMapper.getCustomerMemberInfoById(memberId);
            log.debug("개인고객 정보 조회: {}", customer);
            if (customer != null && memberPw.equals(customer.getMemberPw())) {
            	log.info("개인고객 로그인 성공");
                result.put("memberInfo", customer);
                return result;
            }
            log.warn("개인고객 로그인 실패 - 비밀번호 불일치 또는 정보 없음");
            break;

        case "기업고객":
            CustomerMember corp = loginMapper.getCorpMemberInfoById(memberId);
            log.debug("기업고객 정보 조회: {}", corp);
            if (corp != null && memberPw.equals(corp.getMemberPw())) {
            	log.info("기업고객 로그인 성공");
                result.put("memberInfo", corp);
                return result;
            }
            log.warn("기업고객 로그인 실패 - 비밀번호 불일치 또는 정보 없음");
            break;

        case "입점업체 대표":
            EntMember entCeo = loginMapper.getEntMemberInfoById(memberId);
            log.debug("입점업체 정보 조회: {}", entCeo);
            if (entCeo != null && memberPw.equals(entCeo.getMemberPw())) {
            	log.info("입점업체 로그인 성공");
                result.put("memberInfo", entCeo);
                return result;
            }
            log.warn("입점업체 로그인 실패 - 비밀번호 불일치 또는 정보 없음");
            break;
            
        case "입점업체 직원":
        	EntMember entEmp = loginMapper.getEntMemberInfoById(memberId);
        	log.debug("입점업체 정보 조회: {}", entEmp);
        	if (entEmp != null && memberPw.equals(entEmp.getMemberPw())) {
        		log.info("입점업체 로그인 성공");
        		result.put("memberInfo", entEmp);
        		return result;
        	}
        	log.warn("입점업체 로그인 실패 - 비밀번호 불일치 또는 정보 없음");
        	break;

        default:
        	log.error("정의되지 않은 회원 유형: {}", memberType);
            return null;
        }
        log.warn("모든 회원 유형 로그인 실패");
        return null;
    }
}