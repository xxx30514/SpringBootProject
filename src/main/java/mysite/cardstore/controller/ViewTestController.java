package mysite.cardstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mysite.cardstore.admin.pojo.User;

@Controller
public class ViewTestController {
	
	@GetMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("msg","你好");
		return "success";
	}
	@GetMapping("/login")
	public String adminLoginPage() {
		
		return "admin_login";
	}
	
	@PostMapping("/login")
	//避免表單重複提交
	public String adminLogin(User user,HttpSession session,Model model) {
		if(!ObjectUtils.isEmpty(user.getUserName())&& !ObjectUtils.isEmpty(user.getPassword())) {
			//儲存登入成功的使用者
			session.setAttribute("loginUser", user);
			//管理員登入成功重定向到後台首頁
			return "redirect:/admin";
		}else {
			model.addAttribute("msg","帳號或密碼錯誤");
			//沒成功返回登入頁
			return "admin_login";
		}
	}
	
	@GetMapping("/admin")
	//到後台首頁
	public String adminIndex(HttpSession session,Model model) {
		//是否登入 攔截器
		Object loginUser = session.getAttribute("loginUser");
		if (loginUser!=null) {
			return "admin_index";
		}else {
			//回到登入頁面
			model.addAttribute("msg","尚未登入");
		}
		return "admin_login";
	}
}
