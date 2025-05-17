package ks54team01.customer.register.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks54team01.customer.register.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/register")
@Slf4j
public class EmailAuthController {
	
	 private final EmailService emailService;
	 
    // 이메일 인증코드 전송
    @PostMapping("/sendCode")
    @ResponseBody
    public Map<String, Object> sendCode(@RequestParam String emailFirst,
                                                        @RequestParam String emailLast) {
        String fullEmail = emailFirst + emailLast;
        Map<String, Object> response = new HashMap<>();

        try {
            emailService.sendVerificationEmail(fullEmail);

            response.put("status", "success");
            response.put("message", "인증 코드가 전송되었습니다.");
            response.put("statusCode", 200);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            response.put("status", "fail");
			response.put("message", "이메일 전송에 실패했습니다."); 
            response.put("statusCode", 500);            
            
        }
        return response;
    }

    // 이메일 인증코드 확인
    @PostMapping("/verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestParam String email,
                                                          @RequestParam String code) {
        boolean isVerified = emailService.verifyCode(email, code);

        Map<String, Object> response = new HashMap<>();
        if (isVerified) {
            response.put("status", "success");
            response.put("message", "이메일 인증에 성공했습니다.");
        } else {
            response.put("status", "fail");
            response.put("message", "인증코드가 일치하지 않습니다.");
        }

        return ResponseEntity.ok(response);
    }
	
}
