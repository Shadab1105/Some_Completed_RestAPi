package com.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Author 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aId;
	
	@JsonProperty("aName")
	private String aName;
	
	private Long number;
	
	private String address;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	
	private LocalDate dob;
	
	@ManyToMany(mappedBy = "authors",cascade = CascadeType.ALL)
	
	private Set<Book> books;
	
}



/*
 * // Ye inverse side hoti hai, jo mapping ko maintain karti hai.
 * 
 * // mappedBy = "authors" → Ye indicate karta hai ki relationship Book entity
 * me define hai.
 * 
 * // mappedBy use karne se duplicate table creation avoid hota hai.
 */