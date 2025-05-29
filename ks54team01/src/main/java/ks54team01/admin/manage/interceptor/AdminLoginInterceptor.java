package ks54team01.admin.manage.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class AdminLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("====[ 인터셉터 진입 ]====");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/admin/login");
			return false;
		}
		
		Object loginAdmin = session.getAttribute("loginAdmin");
		if (loginAdmin == null) {
			response.sendRedirect("/admin/login");
			return false;
		}

		
	    
		
		String adminId = (String) session.getAttribute("adminId");
		String memberType = (String) session.getAttribute("memberType");
		
		
		if(adminId == null || !"플랫폼직원".equals(memberType)) {
			response.sendRedirect("/admin/login");
	        return false;
		}
		
		return true;
	}
}
