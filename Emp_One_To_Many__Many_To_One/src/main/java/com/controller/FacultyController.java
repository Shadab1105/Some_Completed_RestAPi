package com.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.Faculty;
import com.service.FacultyServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FacultyController 
{
	
	@Autowired
	private FacultyServiceImpl fs;
	
	
	@PostMapping(path = "/saveFData")
	public ResponseEntity<?> saveFacultyData(@RequestBody Faculty f) throws ResourceNotFoundException
	{
			System.out.println("Id ="+f.getDept().getDId());
		System.out.println(f);
		 System.out.println("Received Faculty: " + f);
		    System.out.println("Department ID: " + (f.getDept() != null ? f.getDept().getDId() : "NULL"));   // ✅ Check for null safety
		return fs.savefaculty(f);
		
	}
	
	@GetMapping(path = "/findFAll")
	public List<?> getAllFacultyData()
	{
		return fs.getAllfaculty();
	}
	
	@GetMapping(path = "/findByFId/{id}")
	public ResponseEntity<?> getFacultyDataById(@PathVariable Long id) throws ResourceNotFoundException
	{
		return fs.getByFId(id);
	}
	
	@GetMapping(path = "/deleteByFId/{id}")
	public ResponseEntity<?> deleteFacultyData(@PathVariable Long id) throws ResourceNotFoundException
	{
		return fs.deleteByFId(id);
	}
	
	@GetMapping(path = "/updateByFId/{id}")
	public ResponseEntity<?> updateFacultyData(@PathVariable Long id,Faculty f) throws ResourceNotFoundException
	{
		return fs.updatefaculty(id, f);
	}
}
