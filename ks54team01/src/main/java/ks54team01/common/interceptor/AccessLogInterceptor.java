package ks54team01.common.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessLogInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Set<String> paramKeys = request.getParameterMap().keySet();		
		
		StringJoiner param = new StringJoiner(", ");
		
		for(String paramKey : paramKeys) {
			param.add(paramKey + ": " + request.getParameter(paramKey));
		}
		
		log.info("ACCESS INFO===================================================");
		log.info("PORT			::::::		{}", request.getLocalPort());
		log.info("SERVERNAME		::::::		{}", request.getServerName());
		log.info("HTTP METHOD		::::::		{}", request.getMethod());
		log.info("URI			::::::		{}", request.getRequestURI());
		log.info("CLIENT IP		::::::		{}", getClientIp(request));
		if(param.toString().length() > 0) {			
			log.info("PARAMETER		::::::		{}", param);
		}
		log.info("==============================================================");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
		
	}

	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(ip != null && ip.contains(",")) {
			ip = ip.split(",")[0].trim();
		}
		if(ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if(ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		return ip;
	}
}
