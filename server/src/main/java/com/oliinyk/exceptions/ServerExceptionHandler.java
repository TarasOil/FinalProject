package com.oliinyk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.oliinyk.domain.response.ErrorMessage;

@ControllerAdvice
public class ServerExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleExceptions(Exception ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(UserServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = TeacherServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(TeacherServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = StudentServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(StudentServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	@ExceptionHandler(value = SingerServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(SingerServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = SongServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(SongServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = ScheduleServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(ScheduleServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = ExamServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(ExamServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
	
	@ExceptionHandler(value = CompetitionServiceException.class)
	public ResponseEntity<ErrorMessage> handleExceptions(CompetitionServiceException ex, WebRequest req) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, ex.getStatus());
	}
}
