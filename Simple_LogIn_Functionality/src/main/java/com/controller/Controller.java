package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.LoginData;
import com.service.LoginService;

@RestController
public class Controller 
{
	@Autowired
	private LoginService ls;

	@PostMapping("saveUser")
	public ResponseEntity<?> saveUser(@RequestBody LoginData user)
	{
		ResponseEntity<?> saveUser = ls.saveUser(user);
		return saveUser;
	}
	@PostMapping("/log-in")
	public ResponseEntity<?> findUser(@RequestBody LoginData user)
	{
		ResponseEntity<?> findUser = ls.findData(user);
		return findUser;
	}
}
