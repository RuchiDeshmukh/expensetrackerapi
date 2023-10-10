package com.expensetrackerapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.expensetrackerapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ExpenseNotFoundException ex, WebRequest request){
		
		ErrorObject object = new ErrorObject();
		object.setMessage(ex.getMessage());
		object.setStatusCode(HttpStatus.NOT_FOUND.value());
		object.setTimestamp(new Date());
		return new ResponseEntity<>(object,HttpStatus.NOT_FOUND);
	}

	//generalized exception for resource not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleResourceNotFoundException(ExpenseNotFoundException ex, WebRequest request){
		
		ErrorObject object = new ErrorObject();
		object.setMessage(ex.getMessage());
		object.setStatusCode(HttpStatus.NOT_FOUND.value());
		object.setTimestamp(new Date());
		return new ResponseEntity<>(object,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
		
		ErrorObject object = new ErrorObject();
		object.setMessage(ex.getMessage());
		object.setStatusCode(HttpStatus.BAD_REQUEST.value());
		object.setTimestamp(new Date());
		return new ResponseEntity<>(object,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){
		
		ErrorObject object = new ErrorObject();
		object.setMessage(ex.getMessage());
		object.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		object.setTimestamp(new Date());
		return new ResponseEntity<>(object,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
