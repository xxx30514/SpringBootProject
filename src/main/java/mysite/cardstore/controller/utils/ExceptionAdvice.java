package mysite.cardstore.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//異常處理器
@RestControllerAdvice
public class ExceptionAdvice {
	//攔截所有異常
	@ExceptionHandler
	public Result doException(Exception exception) {
		exception.printStackTrace();
		if (exception.getMessage().contains("Duplicate entry")) {
			String[] split = exception.getMessage().split(" ");
			String msg = split[2]+"已存在";
			return new Result(msg);
		}
		return new Result("發生異常，請稍後再試!");
	}
}
