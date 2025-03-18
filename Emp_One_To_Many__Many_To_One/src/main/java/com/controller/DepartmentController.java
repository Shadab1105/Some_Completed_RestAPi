package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.service.DepartmentServiceImpl;
import com.service.FacultyServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DepartmentController 
{
	@Autowired
	private DepartmentServiceImpl ds;
	
	
	@PostMapping(path = "/saveData")
	public ResponseEntity<?> saveDepartmentData(@Valid @RequestBody com.model.Department f)
	{
		System.out.println(f);
		return ds.saveDepartment(f);
	}
	
	@GetMapping(path = "/findAll")
	public List<?> AllDepartmentDetail()
	{
		return ds.getAllDepartmentDetail();
	}
	
	
	@GetMapping(path = "/findDept")
	public List<?> Department()
	{
		return ds.getDepartmentDetail();
	}
	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<?> getFacultyDataById(@PathVariable Long id) throws ResourceNotFoundException
	{
		return ds.getById(id);
	}
	
	@GetMapping(path = "/deleteById/{id}")
	public ResponseEntity<?> deleteDepartmentData(@PathVariable Long id) throws ResourceNotFoundException
	{
		return ds.deleteById(id);
	}
	
	@GetMapping(path = "/updateById/{id}")
	public ResponseEntity<?> updateDepartmentData(@PathVariable Long id,com.model.Department f) throws ResourceNotFoundException
	{
		return ds.updateDepartment(id, f);
	}

}
