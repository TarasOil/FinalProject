package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamServiceException extends RuntimeException {

	private static final long serialVersionUID = 8062026055918852120L;
	
	private HttpStatus status;

	public ExamServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
