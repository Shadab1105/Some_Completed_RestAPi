package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> 
{
//	public Optional<Author> findByNumber(Long number);

	public Optional<Author> findByNumber(Long number);
//	public Author findByAName(String aName);

	public Optional<Author> findByaName(String aName);
	

}
