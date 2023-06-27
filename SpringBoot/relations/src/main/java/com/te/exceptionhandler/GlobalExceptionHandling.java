package com.te.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.controller.customexception.RegistrationUnSuccessfullException;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> name(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().stream().forEach(error -> {
			String field = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			map.put(field, defaultMessage);
		});
		return map;
	}

	@ExceptionHandler(RegistrationUnSuccessfullException.class)
	public Map<String, String> registrationUnSuccessfullException(RegistrationUnSuccessfullException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", ex.getMessage());
		return map;

	}

}
