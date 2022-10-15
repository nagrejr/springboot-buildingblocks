package com.newlife.springbootbuildingblocks.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//global exception handler
//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), "From MethodArgumentNotValid",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorInfo, status.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupported
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), "From MethodArgumentNotSupported",
				ex.getMessage());
		return new ResponseEntity<Object>(customErrorInfo, status.METHOD_NOT_ALLOWED);
	}

	// handleUserNameNotFound
	// exception handler
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFound(UserNameNotFoundException ex, WebRequest request) {

		CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorInfo, HttpStatus.NOT_FOUND);
	}

	// handleConstraintViolationException
	// exception handler
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

		CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorInfo, HttpStatus.BAD_REQUEST);
	}
}
