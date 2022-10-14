package mysite.cardstore.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mysite.cardstore.admin.pojo.Emp;
import mysite.cardstore.service.EmpService;
/**
 * 
 * @author yeh
 *Spring Boot 自動配置HandlerMapping 處理各種請求
 *1.RequestMappingHandlerMapping 解析所有方法映射路徑@RequestMapping("/hello")
 *2.WelcomePageHandlerMapping /=>index.html
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
	@Autowired
	EmpService empService;
	
	@RequestMapping("/hello")
	public String handle01() {
		return "你好 Spring Boot initializer";
	}
	//spring security 有設定衝突 post方法會報錯403 尚待解決  
	//<input type="hidden" name="_method" value="delete"> 
	@DeleteMapping("/user")
	public String deleteUser() {
		return "delete 測試";
	}
	@GetMapping("/user")
	public String getUser() {
		return "get 測試";
	}
	
	@GetMapping("/car/{id}/owner/{username}")
	public Map<String, Object> getCar(@PathVariable("id")Integer id,
									  @PathVariable("username") String username,
									  @RequestHeader Map<String, String> header,
									  @RequestParam Map<String, String> params,
									  @CookieValue("JSESSIONID") String jsessionId
			) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("username", username);
		map.put("header", header);
		map.put("params", params);
		map.put("JSESSIONID", jsessionId);
		
		
		return map;
	}
	@PostMapping(value = "/save",produces = "application/json;charset=utf-8")
	public Map postMethod(@RequestBody String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("content", content);
		//中文會轉碼
		return map;
	}
	
	@GetMapping(value = "/emp/{id}",produces = "application/json;charset=utf-8")
	public Emp getEmpById(@PathVariable("id")Integer empId) {
		
		return empService.getEmpById(empId);
	}

}
