package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book>  findByName(String name);
}
