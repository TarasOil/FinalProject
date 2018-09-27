package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionServiceException extends RuntimeException {

	private static final long serialVersionUID = -6277024045956180529L;

	private HttpStatus status;
	
	public CompetitionServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
