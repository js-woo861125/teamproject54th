package ks54team01.customer.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.login.mapper.LoginMapper;
import ks54team01.customer.login.service.LoginService;
import ks54team01.customer.member.domain.CommonMember;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.member.domain.EntMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> matchMember(String memberId, String memberPw) {
        Map<String, Object> resultMap = new HashMap<>();

        CommonMember memberInfo = loginMapper.getMemberInfoById(memberId);
        if (memberInfo == null) {
            log.warn("존재하지 않는 아이디");
            return null;
        }

        String memberType = memberInfo.getMemberType();
        String memberWithdrawStatus = memberInfo.getMemberWithdrawStatus();
        String encodedPw = memberInfo.getMemberPw(); 

        if (memberType == null) {
            log.warn("회원 유형 없음, 로그인 실패");
            return null;
        }

        if ("Y".equals(memberWithdrawStatus)) {
            log.warn("탈퇴한 회원: {}", memberId);
            resultMap.put("memberWithdrawStatus", memberWithdrawStatus);
            resultMap.put("memberType", memberType);
            return resultMap;
        }
		
		if (!passwordEncoder.matches(memberPw, encodedPw)) {
			log.warn("로그인 실패: 비밀번호 불일치"); return null; 
		}
        
        resultMap.put("memberType", memberType);

        switch (memberType) {
            case "개인고객":
                CustomerMember customer = loginMapper.getCustomerMemberInfoById(memberId);
                log.debug("개인고객 정보 조회: {}", customer);
                if (customer != null) {
                    log.info("개인고객 로그인 성공");
                    resultMap.put("memberInfo", customer);
                    return resultMap;
                }
                break;

            case "기업고객":
                CustomerMember corp = loginMapper.getCorpMemberInfoById(memberId);
                log.debug("기업고객 정보 조회: {}", corp);
                if (corp != null) {
                    log.info("기업고객 로그인 성공");
                    resultMap.put("memberInfo", corp);
                    return resultMap;
                }
                break;

            case "입점업체 대표":
                EntMember entCeo = loginMapper.getEntMemberInfoById(memberId);
                log.debug("입점업체 대표 정보 조회: {}", entCeo);
                if (entCeo != null) {
                    log.info("입점업체 대표 로그인 성공");
                    resultMap.put("memberInfo", entCeo);
                    return resultMap;
                }
                break;

            case "입점업체 직원":
                EntMember entEmp = loginMapper.getEntMemberInfoById(memberId);
                log.debug("입점업체 직원 정보 조회: {}", entEmp);
                if (entEmp != null) {
                    log.info("입점업체 직원 로그인 성공");
                    resultMap.put("memberInfo", entEmp);
                    return resultMap;
                }
                break;

            default:
                log.error("정의되지 않은 회원 유형: {}", memberType);
                return null;
        }

        log.warn("로그인 실패: 비밀번호 불일치 또는 정보 없음");
        return null;
    }
}
