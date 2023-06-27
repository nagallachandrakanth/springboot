package com.te.practice.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.practice.customexception.RegistrationUnSuccessfullException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		Map<String, String> map = new HashMap<String, String>();
		exception.getBindingResult().getAllErrors().stream().forEach(error -> {
			String field = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			map.put(field, defaultMessage);
		});
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(RegistrationUnSuccessfullException.class)
	public Map<String, String> registrationUnSuccessfullException(RegistrationUnSuccessfullException exception ) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("message", exception.getMessage());
		return map;
		
	}

}
