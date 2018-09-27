package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingerServiceException extends RuntimeException {

	private static final long serialVersionUID = 4353917932596979207L;
	
	private HttpStatus status;
	
	public SingerServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
