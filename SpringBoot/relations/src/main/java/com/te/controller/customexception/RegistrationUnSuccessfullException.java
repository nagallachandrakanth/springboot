package com.te.controller.customexception;

public class RegistrationUnSuccessfullException extends RuntimeException {
	public RegistrationUnSuccessfullException(String message) {
		super(message);
	}

}
