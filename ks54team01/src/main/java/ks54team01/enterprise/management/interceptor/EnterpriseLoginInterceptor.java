package ks54team01.enterprise.management.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class EnterpriseLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String loginId = (String) session.getAttribute("loginId");
		String loginMemberType = (String) session.getAttribute("loginMemberType");
		
		boolean isProcess = true;
		
		if(loginId == null) {
			isProcess = false;
			response.sendRedirect("/customer/login/memberLogin");
		}else {
			if(!"입점업체 대표".equals(loginMemberType) && !"입점업체 직원".equals(loginMemberType)) {
				isProcess = false;
				response.sendRedirect("/customer/login/memberLogin");
			}
		}
		
		return isProcess;
	}
}
