package mysite.cardstore.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.Result;

import mysite.cardstore.model.User;
import mysite.cardstore.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	//登入
	public Result login(HttpServletRequest request, @RequestBody User user){
		//1.將頁面提交的密碼進行加密
		String password = user.getUserPassword();
		//password = DigestUtils.md5DigestAsHex(password.getBytes());
		//2.根據提交的帳號查詢資料庫是否有該帳號
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
		lqw.eq(User::getUserAccount,user.getUserAccount());
		User userResult = userService.getOne(lqw);
		//3.沒有該帳號返回登入失敗結果
		if (userResult==null) {
			return new Result("查無該帳號，請重新登入");
		}
		//4.確認密碼一致,若不一致返回失敗
		if (!userResult.getUserPassword().equals(password)) {
			return new Result("密碼錯誤，請重新登入");
		}
		//5.判斷帳號是否啟用
		if (0==userResult.getUserStatus()) {
			return new Result("帳號未啟用，請洽詢客服人員");
		}
		//6.登入成功 將使用者id存入session
		request.getSession().setAttribute("loginUser", userResult.getUserId());
		return new Result(true,userResult);
	}
	
	@PostMapping("/logout")
	//登出
	public Result logout(HttpServletRequest request){
		//清除session
		request.getSession().removeAttribute("loginUser");
		return new Result(true);
	}
	
	@PostMapping
	public Result save(@RequestBody User user) {
		log.info("新增使用者:{}",user.toString());
		return new Result(true,userService.saveUser(user));
	}
	@PutMapping
	public Result update(@RequestBody User user) {
	
		return new Result(true,userService.updateUser(user));
	}
}
