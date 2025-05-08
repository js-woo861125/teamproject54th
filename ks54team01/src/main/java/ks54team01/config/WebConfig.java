package ks54team01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ks54team01.common.interceptor.AccessLogInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{

	private final AccessLogInterceptor accessLogInterceptor;
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
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
