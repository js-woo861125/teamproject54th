package snippet;

public class Snippet {
	spring.application.name=ks54team01
	
	# 서버포트 설정
	server.port=80
	
	# thymeleaf 새로고침 적용
	# spring.thymeleaf.cache=false
	
	# datasource 설정 (데이터베이스 연결정보 설정)
	spring.datasource.driver-class-name=net.sf.log4jdbc.sql.jdbcapi.DriverSpy
	#jdbc:mysql://localhost:3306/ksmart54db?
	#serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true
	spring.datasource.url=jdbc:log4jdbc:mysql://129.154.63.122:3306/ks54team01db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true
	spring.datasource.username=ks54team01id
	spring.datasource.password=ks54team01pw
	
	# log 설정 파일 위치 설정
	logging.config=classpath:logback-spring.xml
	
	# mybatis 설정
	# mapper 파일 경로 설정
	mybatis.mapper-locations=classpath:mapper/**/*.xml
	# mapper resultType parameterType domain 패키지 축약
	mybatis.type-aliases-
}

