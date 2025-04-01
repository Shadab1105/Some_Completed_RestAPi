package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Author;

import org.springframework.stereotype.Repository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	 Optional<Author> findByName(String name);  
}
