package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exception.ResourceNotFoundException;
import com.model.Student;

public interface StudentSeriveInterface 
{
	public ResponseEntity<Student> saveEmployeeDetail(Student e);
	public List<Student> getEmployeeDetail();
	public ResponseEntity<Student> getEmployeeDetailByID(Long id) throws ResourceNotFoundException;
	public ResponseEntity<Student> deleteEmployeeDetailById(Long id) throws ResourceNotFoundException;
	public ResponseEntity<Student> updateData(Long id, Student e) throws ResourceNotFoundException;
	
	
//	public ResponseEntity<Employee> SaveEmployee(Employee employee);
//	public List<Employee> getAllEmployees() ;
//	public ResponseEntity<Employee> getEmployeeById(Long id) throws ResourceNotFoundException;
//	public ResponseEntity<Employee> UpdateEmployee(Long id, Employee employee) throws ResourceNotFoundException ;
//	public ResponseEntity<Employee> DeleteEmployee(Long id) throws ResourceNotFoundException;
	

}
