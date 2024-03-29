package mysite.cardstore.controller;

import java.awt.Font;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.R;
import mysite.cardstore.controller.utils.Result;
import mysite.cardstore.param.UserCheckParam;
import mysite.cardstore.param.UserLoginParam;
import mysite.cardstore.pojo.User;
import mysite.cardstore.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	private LineCaptcha lineCaptcha;

	@PostMapping("/login")
	// 登入
	public Result login(HttpServletRequest request, @RequestBody User user) {
		// 1.將頁面提交的密碼進行加密
		String password = user.getUserPassword();
		// password = DigestUtils.md5DigestAsHex(password.getBytes());
		// 2.根據提交的帳號查詢資料庫是否有該帳號
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
		lqw.eq(User::getUserAccount, user.getUserAccount());
		User userResult = userService.getOne(lqw);
		// 3.沒有該帳號返回登入失敗結果
		if (userResult == null) {
			return new Result("查無該帳號，請重新登入");
		}
		// 4.確認密碼一致,若不一致返回失敗
		if (!userResult.getUserPassword().equals(password)) {
			return new Result("密碼錯誤，請重新登入");
		}
		// 5.判斷帳號是否啟用
		if (0 == userResult.getUserStatus()) {
			return new Result("帳號未啟用，請洽詢客服人員");
		}
		// 6.登入成功 將使用者id存入session
		request.getSession().setAttribute("loginUser", userResult.getUserId());
		return new Result(true, userResult);
	}

	@PostMapping("/logout")
	// 登出
	public R logout(HttpServletRequest request) {
		// 清除session
		request.getSession().removeAttribute("loginUser");
		return R.success("登出成功");
	}

	@PostMapping
	public Result save(@RequestBody User user) {
		log.info("新增使用者:{}", user.toString());
		return new Result(true, userService.saveUser(user));
	}

	@PutMapping
	public Result update(@RequestBody User user) {

		return new Result(true, userService.updateUser(user));
	}

	/**
	 * 檢查帳號是否已經存在
	 *
	 * @param userCheckParam 接收檢查帳號的實體 內部有參數userAccount進行校驗
	 * @param result         校驗結果的實體
	 * @return 封裝好的json物件Result
	 */
	@PostMapping("/check")
	public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
		// 檢查是否符合校驗規則 符合false 不符合true
		boolean b = result.hasErrors();
		if (b) {
			return R.fail("請輸入您的帳號");
		}
		return userService.check(userCheckParam);
	}

	@PostMapping("/register")
	public Result register(@RequestBody @Validated User user, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			return new Result("註冊失敗，請稍後再試");
		}
		return userService.register(user);
	}

	@PostMapping("/newlogin")
	public Result newlogin(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
		// 檢查是否符合校驗規則 符合false 不符合true
		boolean b = result.hasErrors();
		if (b) {
			return new Result("請輸入您的帳號");
		}
		return userService.login(userLoginParam);
	}

	// postman測試過
	@PostMapping("/newlogin2")
	public R newlogin2(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result,
			HttpServletRequest request) {
		// 檢查是否符合校驗規則 符合false 不符合true
		boolean b = result.hasErrors();
		if (b) {
			return R.fail("請輸入您的帳號與密碼");
		}
		System.out.println("登入驗證碼" + lineCaptcha.getCode());
//		String verCode = lineCaptcha.getCode();
//		boolean verify = lineCaptcha.verify(userLoginParam.getVerCode().toLowerCase());

//		if (!verify) {
//			return R.fail("驗證碼錯誤");
//		}
//		if (!userLoginParam.getVerCode().equalsIgnoreCase(verCode)) {
//
//			return R.fail("驗證碼錯誤");
//		}
		if (!lineCaptcha.verify(userLoginParam.getVerCode())) {
			return R.fail("驗證碼錯誤");
		}
//		if (!verCode.equalsIgnoreCase(userLoginParam.getVerCode())) {
//			return R.fail("驗證碼錯誤");
//		}
		// request.setAttribute("loginUser", userLoginParam.getUserAccount());
		Object attribute = request.getAttribute("loginUser");
		System.out.println("abc" + attribute);// null
		return userService.login2(request, userLoginParam);
	}

	@GetMapping("/getCode")
	public void getCode(HttpServletResponse response) {
		lineCaptcha = CaptchaUtil.createLineCaptcha(130, 30, 5, 3);
		Font font = new Font("Times New Roman", Font.PLAIN, 28);
		lineCaptcha.setFont(font);
		System.out.println("驗證碼" + lineCaptcha.getCode());
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			lineCaptcha.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
