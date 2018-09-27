package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleServiceException extends RuntimeException {

	private static final long serialVersionUID = 2861182635892818737L;
	
	private HttpStatus status;

	public ScheduleServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
