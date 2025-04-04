package com.exception;

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
public class GlobalExceptionHandler 
{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest req)
	{
		ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
		
	}
	
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) 
	 {
	        Map<String, String> resp = new HashMap<>();
	        ex.getBindingResult().getAllErrors().
	        forEach((error)->
	        		{
	        			String fieldName = ((FieldError)error).getField();
	        			String message = error.getDefaultMessage();
	        			resp.put(fieldName,message);
	        		});
	        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	    }
	

}
