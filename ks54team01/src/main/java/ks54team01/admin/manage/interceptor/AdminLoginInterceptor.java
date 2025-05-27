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
		
		HttpSession session = request.getSession();
		
		String adminId = (String) session.getAttribute("adminId");
		
		boolean isProcess = true;
		
		if(adminId == null) {
			isProcess = false;
			response.sendRedirect("/admin/login");
		}else {
			String memberType = (String) session.getAttribute("memberType");
			if(!"플랫폼직원".equals(memberType)) {
				isProcess = false;
				response.sendRedirect("/admin/login");
			}
		}
		
		return isProcess;
	}
}
