package com.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Role;
import com.repository.RoleRepository;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private RoleRepository rr;
	
	@GetMapping("/hello")
	public String Home()
	{
		return "Hello!!! Admin";
	}
	@GetMapping(path = "/findAll")
	public List<Role> AllDepartmentDetail()
	{
		return rr.findAll();
	}
	
}
