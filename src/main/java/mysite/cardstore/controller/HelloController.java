package mysite.cardstore.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ResponseBody+@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String handle01() {
		return "你好 Spring Boot initializer";
	}
	
	@RequestMapping(value = "/user",method = RequestMethod.DELETE)
	public String deleteUser() {
		return "delete 測試";
	}
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String getUser() {
		return "get 測試";
	}
}
