package ks54team01.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessLogInterceptor implements HandlerInterceptor{

	private final AdminProductInfoMapper adminProductInfoMapper;

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
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String requestUri = request.getRequestURI();
		if(requestUri != null && (requestUri.indexOf("/customer") > -1 || requestUri.equals("/"))) {
			List<ProductInfoCategory> mdCategoryList = adminProductInfoMapper.getMdCategory();
			modelAndView.addObject("mdCategoryList", mdCategoryList);
		
			 Map<String, List<ProductInfoCategory>> smCategoryMap = new HashMap<>();

		        for (ProductInfoCategory mdCategory : mdCategoryList) {
		            String mdCategoryNo = mdCategory.getMdCategoryNo();  // 중분류 코드
		            
		            List<ProductInfoCategory> smCategoryList = adminProductInfoMapper.getSmCategoryByMdCategoryNo(mdCategoryNo);

		            smCategoryMap.put(mdCategoryNo, smCategoryList);
		        }

		        // 뷰에서 사용할 수 있도록 모델에 추가
		        modelAndView.addObject("smCategoryMap", smCategoryMap);
		  }
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
