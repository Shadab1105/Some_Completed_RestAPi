package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RoleDTO;
import com.exception.ResourceNotFoundException;
import com.model.Role;
import com.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController 
{
	@Autowired
	private RoleRepository rr;
	
	@PostMapping(path = "/saveData")
	public Role saveRolesData(@RequestBody Role r)
	{
		System.out.println(r);
		return r;
	}
	
	@GetMapping(path = "/findAll")
	public List<Role> AllDepartmentDetail()
	{
		return rr.findAll();
	}
	
	
	@GetMapping(path = "/findById/{id}")
	public Optional<Role> getFacultyDataById(@PathVariable Long id) throws ResourceNotFoundException
	{
		Optional<Role> findById = rr.findById(id);
		return findById;
	}
	
//	@GetMapping(path = "/deleteById/{id}")
//	public ResponseEntity<?> deleteDepartmentData(@PathVariable Long id) throws ResourceNotFoundException
//	{
//		return rr.deleteById(id);
//	}
	

}
