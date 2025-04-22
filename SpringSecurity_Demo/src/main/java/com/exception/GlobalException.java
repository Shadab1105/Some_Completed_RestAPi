package com.exception;

import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.JwtException;

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
	
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Map<String,String>> HandelAuthenticationException(AuthenticationException ex)
	{
		Map<String, String> map = new HashMap<>();
		
		map.put("error","Authentication Failed");
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<Map<String,String>> handelJwtException(JwtException ex)
	{
		Map<String, String> map = new HashMap<>();
		
		map.put("error","Invalid Jwt Token");
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handelException(Exception ex)
	{
		Map<String, String> map = new HashMap<>();
		
		map.put("error","Internal Server Error");
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	

}
