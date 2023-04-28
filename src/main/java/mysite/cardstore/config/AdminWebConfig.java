package mysite.cardstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import mysite.cardstore.interceptor.LoginInterceptor;
/**
 * 
 * @author yeh
 * 1.配置攔截器要攔截那些請求 實現implements HandlerInterceptor介面
 * 2.配置攔截器至IOC容器中(註冊組件) implements WebMvcConfigurer
 * 3.指定攔截規則
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/**") //攔截所有請求 包括靜態資源
		.excludePathPatterns("/","/login","/css/**","/dist/**","/img/**","/js/**","/plugins/**","/index.html",
				"/emps/**","/users/**","/backend/login.html","/favicon.ico","/categorys/list","/error","/carts/**","/products/cart/detail");
	}
}
