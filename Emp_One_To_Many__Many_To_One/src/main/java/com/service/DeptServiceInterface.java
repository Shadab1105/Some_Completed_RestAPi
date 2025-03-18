package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exception.ResourceNotFoundException;
import com.model.Department;

public interface DeptServiceInterface 
{
	public ResponseEntity<?> saveDepartment(Department d);
	public List<?> getAllDepartmentDetail();
	public List<?> getDepartmentDetail();
	public ResponseEntity<?> getById(Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> deleteById(Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> updateDepartment(Long id,Department d) throws ResourceNotFoundException;

}
