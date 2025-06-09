package com.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.LoginData;
import com.repository.loginRepository;

@Service
public class LoginService 
{
	@Autowired
	private loginRepository lr;
	
	 private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public ResponseEntity<?> saveUser(LoginData l)
	{
		Optional<LoginData> byId = lr.findById(l.getUsername());
		if(byId.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Message","Found this UserName Please Change "));
		}
	
		l.setPassword(passwordEncoder.encode(l.getPassword()));
		LoginData save = lr.save(l);
		return ResponseEntity.ok(Map.of("Data",save));
		
	}
	
	public ResponseEntity<?> findData(LoginData l)
	{
		Optional<LoginData> byId = lr.findById(l.getUsername());
		System.out.println("Data = "+byId);
		System.out.println("SendData = "+l);
		
		if(byId.isPresent())
		{
			LoginData uData = byId.get();
			if(passwordEncoder.matches(l.getPassword(),uData.getPassword()))
			{
			return ResponseEntity.ok(Map.of("Message","Logged-In"));
			}
			else
			{
				return ResponseEntity.ok(Map.of("Message","Password Mismatch"));	
			}
		}
	
		
		return ResponseEntity.ok(Map.of("Message","Incorrect Username"));
		
	}
	
	
}
