package mysite.cardstore.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@ResponseBody+@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String handle01() {
		return "你好 Spring Boot initializer";
	}
}
