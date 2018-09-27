package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentServiceException extends RuntimeException {

	private static final long serialVersionUID = 331562721020343530L;
	
	private HttpStatus status;
	
	public StudentServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
