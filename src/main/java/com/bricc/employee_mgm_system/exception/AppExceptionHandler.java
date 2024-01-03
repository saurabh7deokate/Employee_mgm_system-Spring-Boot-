package com.bricc.employee_mgm_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bricc.employee_mgm_system.util.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(NotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), String.valueOf(exception.getMessage()) + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(DataNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), String.valueOf(exception.getMessage()) + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(IdNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), String.valueOf(exception.getMessage()) + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

}
