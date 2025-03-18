package com.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.exception.ResourceNotFoundException;
import com.model.Emp;
import com.service.EmpServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller 
{
	
	
	@Autowired
	private EmpServiceImpl es;


	
		@PostMapping(path = "/saveEmp")
		public ResponseEntity<?> saveData(@Valid @RequestBody Emp e)
		{
			Date dob = e.getDob();
			System.out.println(dob);
			ResponseEntity<?> saveEmp = es.saveEmp(e);
			return saveEmp;
			
		}
		
		
		@GetMapping(path ="/getAll")
		public List<?> GetAllEmp()
		{
			List<?> allEmp = es.getAllEmp();
			return allEmp;
			
		}
		
		@GetMapping(path="findById/{id}")
		public ResponseEntity<?> findById(@PathVariable Long id) throws ResourceNotFoundException
		{
			ResponseEntity<?> byId = es.getById(id);
			return byId;
			
			
		}
		
		
		
		@PatchMapping(path = "/updateEmp/{id}")
		public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody Emp e) throws ResourceNotFoundException
		{
			ResponseEntity<?> updateEmp = es.updateEmp(id, e);
			return updateEmp;
			
		}
		
		
		@DeleteMapping(path = "/deleteById/{id}")
		public ResponseEntity<?> deleteById(@PathVariable Long id) throws ResourceNotFoundException
		{
			ResponseEntity<?> deleteById = es.deleteById(id);
			return deleteById;
			
		}
		
		
		
		
		
	
}
