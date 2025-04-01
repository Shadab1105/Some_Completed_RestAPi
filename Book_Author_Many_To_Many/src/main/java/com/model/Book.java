package com.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Book 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bId;
	
	
	
	@JsonProperty("bName")
	private String bName;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate publishDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="book_author",
				joinColumns = @JoinColumn(name="book_id"),
				inverseJoinColumns = @JoinColumn(name="author_id"))
	
	private Set<Author> authors;

}




/*
 * // name → Join table ka naam (yaha book_author).
 * 
 * // joinColumns → Ye current entity (Book) ko refer karta hai.
 * 
 * // name = "book_id" → Join table me book ka reference hoga.
 * 
 * // inverseJoinColumns → Ye opposite entity (Author) ko refer karta hai.
 * 
 * // name = "author_id" → Join table me author ka reference hoga.
 */