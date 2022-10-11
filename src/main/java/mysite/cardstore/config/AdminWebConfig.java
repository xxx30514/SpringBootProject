package mysite.cardstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import mysite.cardstore.interceptor.LoginInterceptor;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/**") //攔截所有請求 包括靜態資源
		.excludePathPatterns("/","/login","/css/**","/dist/**","/img/**","/js/**","/plugins/**");
	}
}
