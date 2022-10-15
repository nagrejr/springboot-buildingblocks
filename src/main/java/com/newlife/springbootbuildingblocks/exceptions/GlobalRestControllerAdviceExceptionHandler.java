package com.newlife.springbootbuildingblocks.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorInfo userNameNotFound(UserNameNotFoundException e) {
		return new CustomErrorInfo(new Date(), "from RestControllerAdvice global Exception handler", e.getMessage());
	}

}
