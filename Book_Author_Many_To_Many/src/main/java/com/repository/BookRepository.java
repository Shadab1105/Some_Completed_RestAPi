package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>
{
//	public Optional<Book> findBybName(String bName);
	
//	public Book findBybName(String bName);
	Optional<Book> findBybName(String bName);
	

}
