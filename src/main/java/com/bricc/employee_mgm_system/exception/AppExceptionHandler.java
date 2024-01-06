package com.bricc.employee_mgm_system.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bricc.employee_mgm_system.util.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(NotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(DataNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(IdNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<>();

		for (ObjectError errors : allErrors) {
			FieldError error = (FieldError) errors;
			map.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
