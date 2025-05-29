package ks54team01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import ks54team01.admin.manage.interceptor.AdminLoginInterceptor;
import ks54team01.common.interceptor.AccessLogInterceptor;
import ks54team01.customer.login.interceptor.CustomerLoginInterceptor;
import ks54team01.enterprise.management.interceptor.EnterpriseLoginInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
	@Value("${file.path}")
	private String fileRealPath;
	
	private final AccessLogInterceptor accessLogInterceptor;
	private final AdminLoginInterceptor adminLoginInterceptor;
	private final CustomerLoginInterceptor customerLoginInterceptor;
	private final EnterpriseLoginInterceptor enterpriseLoginInterceptor;
	
	
	
	/**
	 * addInterceptors : bean으로 등록한 interceptor를 webproject에 추가하는 메소드
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		/**
		 * interceptor를 추가할 때는 registry.addInterceptor(등록한 interceptor)
		 * 적용할 uri .addPathPatterns(주소패턴)
		 * 적용하지 않을 uri .excludePathPatterns("주소패턴");
		 */
		registry.addInterceptor(accessLogInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/manage/**")
				.excludePathPatterns("/user/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/error");
		
		
		registry.addInterceptor(adminLoginInterceptor) 
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/login") 
				.excludePathPatterns("/admin/logout")
				.excludePathPatterns("/manage/**") 
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/error");
		 
		
		registry.addInterceptor(customerLoginInterceptor)
				.addPathPatterns("/customer/wishList/**")
				.addPathPatterns("/customer/member/**")
				.addPathPatterns("/customer/transferBoard/addTransferBoard")
				.addPathPatterns("/customer/transferBoard/myTransferBoardList")
				.addPathPatterns("/customer/assigneeBoard/myAssigneeBoardList")
				.addPathPatterns("/customer/assigneeBoard/addAssigneeBoard")
				.addPathPatterns("/customer/payment/**")
				.addPathPatterns("/customer/delivery/**")
				.excludePathPatterns("/customer/member/forgotId")
				.excludePathPatterns("/customer/member/forgotPw")
				.excludePathPatterns("/customer/login/**")
				.excludePathPatterns("/customer/register/**")
				.excludePathPatterns("/manage/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/error");
		
		
		registry.addInterceptor(enterpriseLoginInterceptor)
				.addPathPatterns("/enterprise/**")
				.excludePathPatterns("/customer/member/pwCheck")
				.excludePathPatterns("/manage/**") 
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/error");
		 
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String osName = System.getProperty("os.name").toLowerCase();
		String rootPath = osName.contains("win") ? "file:///c:" : "file://";
		
		// 특정 패턴의 주소요청이 발생시 실제 파일의 절대 경로(프로젝트 외부 환경)으로 접근
		// c:/home/teamproject/attachment
		registry.addResourceHandler("/attachment/**")
				.addResourceLocations(rootPath + fileRealPath + "/attachment/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}
