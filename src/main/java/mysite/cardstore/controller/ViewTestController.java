package mysite.cardstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import mysite.cardstore.pojo.Emp;
import mysite.cardstore.service.EmpService;

@Controller
public class ViewTestController {

	@Autowired
	EmpService empService;

	@GetMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("msg","你好");
		return "success";
	}
	@GetMapping("/login")
	public String adminLoginPage() {

		return "admin_login";
	}

//	@PostMapping("/login")
//	//避免表單重複提交
//	public String adminLogin(User user,HttpSession session,Model model) {
//		if(!ObjectUtils.isEmpty(user.getUserName())&& !ObjectUtils.isEmpty(user.getPassword())) {
//			//儲存登入成功的使用者
//			session.setAttribute("loginUser", user);
//			//管理員登入成功重定向到後台首頁
//			return "redirect:/admin";
//		}else {
//			model.addAttribute("msg","帳號或密碼錯誤");
//			//沒成功返回登入頁
//			return "admin_login";
//		}
//	}
//
	@GetMapping("/admin")
	//到後台首頁
	public String adminIndex(HttpSession session,Model model) {
		//是否登入 改由攔截器攔截
//		Object loginUser = session.getAttribute("loginUser");
//		if (loginUser!=null) {
//			return "admin_index";
//		}else {
//			//回到登入頁面
//			model.addAttribute("msg","尚未登入");
//		}
		return "admin_index";
	}
	@GetMapping("/data")
	public String adminData(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo ,Model model) {
		List<Emp> list = empService.list();
		model.addAttribute("emps",list);
		//獲取分頁資訊 pageNo=起始頁  5=每頁顯示5筆
		Page<Emp> Emppage = new Page<>(pageNo,5);
		//分頁查詢結果
		Page<Emp> page = empService.page(Emppage, null);
		//List<Emp> records = page.getRecords(); //每頁資料的集合
		model.addAttribute("page",page);
		return "table/data";
	}

	@GetMapping("/emp/delete/{empId}")
	public String deleteEmp(@PathVariable("empId") Integer empId,
							@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
							RedirectAttributes ra) {
		empService.removeById(empId);
		ra.addAttribute("pageNo",pageNo);
		return "redirect:/data";
	}
}
