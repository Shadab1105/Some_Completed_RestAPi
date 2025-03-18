package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.Student;
import com.repository.StudentRepository;

@Service
public class StudentService implements StudentSeriveInterface
{

	@Autowired
	private StudentRepository er;
	
	@Override
	public ResponseEntity<Student> saveEmployeeDetail(Student e) 
	{
		Student saveEmployee = er.save(e);
		return new ResponseEntity<>(saveEmployee,HttpStatus.CREATED);
	}

	@Override
	public List<Student> getEmployeeDetail() 
	{
		try 
			{
			List<Student> AllEmployeeDetail = er.findAll();
			return AllEmployeeDetail;
			}
		
		catch(EmptyResultDataAccessException e)
		{
			System.err.println("No Empoyee Data found in DataBase");
			return new ArrayList<Student>();
		}
	}	

	@Override
	public ResponseEntity<Student> getEmployeeDetailByID(Long id) throws ResourceNotFoundException 
	{
		Student e = er.findById(id).
				 orElseThrow(()-> new ResourceNotFoundException("Employee not found "+ id));
		 
		 return ResponseEntity.ok().body(e);
	}

	@Override
	public ResponseEntity<Student> deleteEmployeeDetailById(Long id) throws ResourceNotFoundException 
	{
		try {
		
			Student deleteEmp = er.findById(id).
		orElseThrow(()-> new ResourceNotFoundException("Employee Detail Not available with id "+ id));
		
		er.deleteById(id);
		
		return ResponseEntity.ok().body(deleteEmp);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new ResourceNotFoundException("Employee table not found with id: " + id);
			
		}
	}

	@Override
	public ResponseEntity<Student> updateData(Long id,Student e) throws ResourceNotFoundException 
	{
		
		Student FindEmpById = er.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Employee Detail Not available with id "+ id));
				
				if(FindEmpById ==null)
				{
					return ResponseEntity.notFound().build();
				}
				Student updateData = er.save(e);
		return ResponseEntity.ok().body(updateData);
	}
	
	
	

}
