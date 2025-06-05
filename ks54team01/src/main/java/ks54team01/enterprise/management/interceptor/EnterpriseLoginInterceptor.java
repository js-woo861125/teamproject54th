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
		String contractStatus= (String) session.getAttribute("entContractStatus");
		
		boolean isProcess = true;
		
		if (loginId == null 
		        || (!"입점업체 대표".equals(loginMemberType) && !"입점업체 직원".equals(loginMemberType))) {
		        response.sendRedirect("/customer/login/memberLogin");
		        isProcess = false;
		        return isProcess;
		    }

	    if (!"계약중".equals(contractStatus)) {
	        response.sendRedirect("/customer/login/memberLogin?message=contractError");
	        isProcess = false;
	    }
		
		return isProcess;
	}
}
