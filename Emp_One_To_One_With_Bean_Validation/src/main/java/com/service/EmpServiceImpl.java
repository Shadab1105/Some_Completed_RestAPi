package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.Emp;
import com.repository.EmpRespository;

@Service
public class EmpServiceImpl implements EmpServiceInterface
{

	@Autowired
	private EmpRespository er;
	
	
	
	@Override
	public ResponseEntity<Map<String, Object>> saveEmp(Emp e) 
	{
		Emp save = er.save(e);
		//return new ResponseEntity<>(save,HttpStatus.CREATED);
		 return ResponseEntity.status(HttpStatus.CREATED)
				 .body(Map.of("message", "Data successfully saved", "data", save));
	}

	
	
	  @Override
	    public List<?> getAllEmp() {
	        List<Emp> all = er.findAll();
	        if (all.isEmpty()) {
	            return List.of(Map.of("message", "Database is empty"));
	        }
	        return all;
	    }
	
	
	
	
	@Override
	public ResponseEntity<?> getById(Long id) throws ResourceNotFoundException 
	{
		 Emp e = er.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found"));
	        return ResponseEntity.ok(Map.of("message", "Employee found", "data", e));
	}

	
	
	
	
	@Override
	public ResponseEntity<?> updateEmp(Long id, Emp e) throws ResourceNotFoundException 
	{
		Emp e1 = er.findById(id).
		orElseThrow(()->new ResourceNotFoundException("Employee Detail is not availble with id "+ id));
		
		e1.setEmpName(e.getEmpName());
		e1.setEmpSalary(e.getEmpSalary());
		e1.setNum(e.getNum());
		e1.setAddress(e.getAddress());
		
		Emp save = er.save(e1);
		
	      return ResponseEntity.ok(Map.of("message", "Data successfully updated", "data", save));
	}
	
	
	public ResponseEntity<?> deleteById(Long id) throws ResourceNotFoundException 
		{
			Emp e2 = er.findById(id).
			orElseThrow(()-> new ResourceNotFoundException("Data mnot found with id ="+id));
			
			er.deleteById(id);
			
		    return ResponseEntity.ok(Map.of("message", "Data successfully deleted"));
		}

}
