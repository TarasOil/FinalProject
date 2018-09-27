package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 6827913842927095234L;

	private HttpStatus status;
	
	public UserServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
