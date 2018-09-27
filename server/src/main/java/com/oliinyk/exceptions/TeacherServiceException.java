package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherServiceException extends RuntimeException {

	private static final long serialVersionUID = 3745703174981570265L;
	
	private HttpStatus status;
	
	public TeacherServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
