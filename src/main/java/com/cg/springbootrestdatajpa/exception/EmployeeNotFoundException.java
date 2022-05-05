package com.cg.springbootrestdatajpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {

	public EmployeeNotFoundException() {

	}
	public EmployeeNotFoundException(String s) {
		super(s);
	}
}
