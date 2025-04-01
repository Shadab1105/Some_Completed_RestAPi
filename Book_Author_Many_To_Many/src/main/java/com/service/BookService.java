package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exception.ResourceNotFoundException;
import com.model.Book;

public interface BookService 
{
	//public ResponseEntity<?> saveBookData(Book b);
	
	public Book saveBookData(Book b);
	public ResponseEntity<?> findById(Long id) throws ResourceNotFoundException;
	public List<?> findAll() ;
	public ResponseEntity<?> updateBookData(Book b,Long id) throws ResourceNotFoundException;
	public ResponseEntity<?> deleteBookData(Long id) throws ResourceNotFoundException;
	

}
