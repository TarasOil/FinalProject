package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongServiceException extends RuntimeException {

	private static final long serialVersionUID = 8455209944205641594L;
	
	private HttpStatus status;
	
	public SongServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
