package mysite.cardstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
/**
 * mybatis+分頁插件配置
 * @author yeh
 *
 */
@Configuration
public class PageHelperConfig {
	
	@Bean
	public MybatisPlusInterceptor pageInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
		paginationInnerInterceptor.setOverflow(true);
		paginationInnerInterceptor.setMaxLimit(500L);
		mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
		
		return mybatisPlusInterceptor;
	}
}