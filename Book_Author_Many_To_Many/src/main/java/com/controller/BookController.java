package com.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.Author;
import com.model.Book;
import com.repository.BookRepository;
import com.service.BookServiceImpl;

@RestController
@RequestMapping(path = "/api")
public class BookController 
{

	@Autowired
	private BookServiceImpl bs;
	
	@Autowired
	private DataSource ds;
	
	@GetMapping(path = "/getCon")
	public String getConnection() throws SQLException 
	{
		Connection con = ds.getConnection();
		if (con != null) {
			return "SuccessFully Establish";
		} else {
			return "Connection not establish";
		}
	}
	
	@PostMapping(path = "/saveBook",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book saveBookDetail(@RequestBody Book b)
	{	
		return bs.saveBookData(b);
	}
	
	@GetMapping(path = "/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) throws ResourceNotFoundException
	{
		return bs.findById(id);
	}
	
	@GetMapping(path = "/getAll")
	public List<?> getAll()
	{
		return bs.findAll();
	}

	@PatchMapping(path = "/updateBook/{id}")
	public ResponseEntity<?> updateData(@RequestBody Book b, @PathVariable Long id) throws ResourceNotFoundException
	{
		return bs.updateBookData(b,id);
	}
	
	@DeleteMapping(path="/deleteById/{id}")
	public ResponseEntity<?> updateData( @PathVariable Long id) throws ResourceNotFoundException
	{
		return bs.deleteBookData(id);
	}

}
