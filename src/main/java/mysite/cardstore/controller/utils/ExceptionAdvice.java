package mysite.cardstore.controller.utils;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

//異常處理器
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Result doException(Exception exception) {
		exception.printStackTrace();
		if (exception.getMessage().contains("Duplicate entry")) {
			String[] split = exception.getMessage().split(" ");
			String msg = split[9]+"已存在";
			return new Result(msg);
		}
		return new Result("發生異常，請稍後再試!");
	}
	
	@ExceptionHandler(CustomException.class)
	public Result doException(CustomException exception) {
		log.error(exception.getMessage());
		exception.printStackTrace();	
		return new Result(exception.getMessage());
	}
}
