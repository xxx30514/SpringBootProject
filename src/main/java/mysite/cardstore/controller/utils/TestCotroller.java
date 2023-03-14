package mysite.cardstore.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCotroller {
	
	@GetMapping("/login2")
	public String adminLoginPage() {
		
		return "admin_login";
	}
}
