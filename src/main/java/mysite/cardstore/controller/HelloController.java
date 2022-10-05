package mysite.cardstore.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author yeh
 *
 *Rest風格實現 要在yam配置文件中開啟
 *spring:
  	mvc:
     hiddenmethod:
      filter:
       enabled: true
 */
@RestController
//@ResponseBody+@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String handle01() {
		return "你好 Spring Boot initializer";
	}
	//spring security 有設定衝突 post方法會報錯403 尚待解決  
	@RequestMapping(value = "/user",method = RequestMethod.DELETE)
	public String deleteUser() {
		return "delete 測試";
	}
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String getUser() {
		return "get 測試";
	}
}
