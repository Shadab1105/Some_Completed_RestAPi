package com.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HandelGlobalException 
{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> HandelResourceNotFoundException(ResourceNotFoundException ex,WebRequest req)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> HandelMethodArgumentException(MethodArgumentNotValidException ex)
	{
		Map<String, String> m = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			m.put(field, defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(m,HttpStatus.BAD_REQUEST);
	}

}
