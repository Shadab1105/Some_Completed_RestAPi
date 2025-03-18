package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exception.ResourceNotFoundException;
import com.model.Faculty;

public interface FacultyServiceInterface 
{
	public ResponseEntity<?> savefaculty(Faculty f) throws ResourceNotFoundException ;
	public List<?> getAllfaculty();
	public ResponseEntity<?> getByFId(Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> deleteByFId(Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> updatefaculty(Long id,Faculty d) throws ResourceNotFoundException;

	
	

}
