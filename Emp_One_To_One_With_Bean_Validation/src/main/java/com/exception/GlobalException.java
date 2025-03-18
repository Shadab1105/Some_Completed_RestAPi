package com.exception;

import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException 
{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> HandelGlobalException(ResourceNotFoundException e, WebRequest req)
	{

		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> HandelMethodArgumentNotValid(MethodArgumentNotValidException ex)
	{
		Map<String, String> map = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			map.put(field,defaultMessage);
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}

}
