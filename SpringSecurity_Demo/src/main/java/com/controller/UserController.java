package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController 
{@Autowired
	private UserRepository ur;
	
@Autowired
private RoleRepository rr;
@Autowired
private BCryptPasswordEncoder pe;


	
	

	@PostMapping(path = "/saveData")
	public User saveFacultyData(@RequestBody User u) throws ResourceNotFoundException
	{
		 u.setPassword(pe.encode(u.getPassword()));
		 System.out.println("user = "+pe.encode(u.getPassword()));
	
		return ur.save(u);
		
	}
	
	@GetMapping(path = "/findAll")
	public List<User> getAllFacultyData()
	{
		return ur.findAll();
	}
	
	@GetMapping(path = "/findById/{id}")
	public Optional<User> getFacultyDataById(@PathVariable String id) throws ResourceNotFoundException
	{
		Optional<User> findUs = ur.findById(id);
		return findUs;
	}
	

}
