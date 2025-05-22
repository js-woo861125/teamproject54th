package ks54team01.admin.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		
		boolean isProcess = true;
		
		if(sessionId == null) {
			isProcess = false;
			response.sendRedirect("/admin/login");
		}else {
			String sessionGrade = (String) session.getAttribute("SGRD");
			if(!"플랫폼직원".equals(sessionGrade)) {
				isProcess = false;
				response.sendRedirect("/admin/login");
			}
		}
		
		return isProcess;
	}
}
