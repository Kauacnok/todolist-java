package br.com.kauacnok.todolist.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		return ResponseEntity.status(400).body(exception.getMostSpecificCause().getMessage());
	}

}