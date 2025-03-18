package com.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Student;
import com.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class Controller 
{
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private StudentService es;
	
	@GetMapping(path = "/checkCon")
	public String DbCon() throws SQLException
	{
		try
		{
			Connection con = ds.getConnection();
			return "Yes!! Connection is establish";
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			return "Ohh NO!! Connextion is not establish";
		}
	}
	
	
	@PostMapping(path = "/saveEmp")
	public ResponseEntity<Student> SaveData(@Validated @RequestBody Student e)
	{
		
		 //  System.out.println("Received Employee Name: " + e.);
	ResponseEntity<Student> saveEmployeeDetail = es.saveEmployeeDetail(e);
	
	return saveEmployeeDetail;
	}
	
	
	@GetMapping(path="/findAll")
	public List<Student> getallEmp() 
	{
	
			return es.getEmployeeDetail();
	}
	
	
	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") Long id) throws com.exception.ResourceNotFoundException
	{
		ResponseEntity<Student> employeeDetailByID = es.getEmployeeDetailByID(id);
		return employeeDetailByID;
	}
	
	
	@GetMapping(path="deleteById/{id}")
	public String deleteById(@PathVariable("id") Long id) throws com.exception.ResourceNotFoundException
	{
		 ResponseEntity<Student> deleteEmployeeDetailById = es.deleteEmployeeDetailById(id);
		 if(deleteEmployeeDetailById!=null)
		 {
			 return "Successfully Deleted Employee Detail ";
		 }
		 else
		 {
			 return "Something Error";
		 }
	}
	
	
	@PatchMapping(path = "/updateData/{id}")
	public ResponseEntity<Student> updatEmpData(@PathVariable Long id,@RequestBody Student e) throws com.exception.ResourceNotFoundException
	{
		ResponseEntity<Student> updatdE = es.updateData(id,e);
		return updatdE;
	}
	
	
	

}
