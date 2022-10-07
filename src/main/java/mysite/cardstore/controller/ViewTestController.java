package mysite.cardstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {
	
	@GetMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("msg","你好");
		return "success";
	}
}
