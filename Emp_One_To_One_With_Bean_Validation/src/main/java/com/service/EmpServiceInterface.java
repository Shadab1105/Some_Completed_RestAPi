package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.exception.ResourceNotFoundException;
import com.model.Emp;

public interface EmpServiceInterface
{
	
	public ResponseEntity<Map<String, Object>> saveEmp(Emp e);
	public List<?> getAllEmp();
	public ResponseEntity<?> getById(Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> updateEmp(Long id,Emp e) throws ResourceNotFoundException;
	public ResponseEntity<?> deleteById(Long id) throws ResourceNotFoundException; 
	
	

}
