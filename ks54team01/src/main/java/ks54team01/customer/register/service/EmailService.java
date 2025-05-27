package ks54team01.customer.register.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    
    /**
     * 비밀번호 찾기
     */
    public void sendPwEmail(String toEmail, String memberId, String tempPw) {
    	String subject = "비밀번호 찾기 안내";
    	String text = memberId + "님의 임시비밀번호를 보내드립니다.";
    	SimpleMailMessage message = new SimpleMailMessage();
    	
    	message.setTo(toEmail);
    	message.setSubject(subject);
    	message.setText(text);
    	message.setText("임시비밀번호: " + tempPw);
    	
    	mailSender.send(message);
    	
    }
    
    /**
     * 임시 비밀번호 생성
     */
    public String generateTempPassword() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    
    /**
     * 아이디 찾기
     */
    public void sendIdEmail(String toEmail, String memberId) {
    	String subject = "아이디 찾기 안내";
    	String text = "요청하신 회원님의 아이디는 다음과 같습니다.\n\n아이디: " + memberId;
    	
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(toEmail);
    	message.setSubject(subject);
    	message.setText(text);
    	
    	mailSender.send(message);
    }
    

    /**
	 * 이메일 → 인증코드 저장소 (메모리)
	 */
    private static final Map<String, String> verificationCodes = new ConcurrentHashMap<>();
    

    /**
	 * 인증 코드 생성 및 전송
	 */
    public void sendVerificationEmail(String toEmail) {
        String code = createVerificationCode();
        verificationCodes.put(toEmail, code);
        
        log.info("이메일 인증 요청 - 대상: {}, 인증코드: {}", toEmail, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("이메일 인증 코드");
        message.setText("인증 코드: " + code);
        mailSender.send(message);
        
    }
    
    
    /**
	 * 인증 코드 검증
	 */
    public boolean verifyCode(String email, String inputCode) {
        return inputCode.equals(verificationCodes.get(email));
    }

    
    /**
	 * 랜덤 6자리 숫자 생성
	 */
    private String createVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }


}
