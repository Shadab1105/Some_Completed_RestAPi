package com.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Author;
import com.model.Book;
import com.model.BookDTO;
import com.repository.BookRepository;
import com.service.BookService;
import com.service.BookServiceImpl;


@RestController
@RequestMapping("/api")
public class BookController 
{
	
	

	    private final BookService bookService;

	    public BookController(BookService bookService) {
	        this.bookService = bookService;
	    }

	    @PostMapping("/book")
	    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
	        return ResponseEntity.ok(bookService.saveBook(book));
	    }

	    @GetMapping("/getById/{id}")
	    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
	        return ResponseEntity.ok(bookService.getBookById(id));
	    }

	    @GetMapping("/getAll")
	    public ResponseEntity<List<Book>> getAllBooks() {
	        return ResponseEntity.ok(bookService.getAllBooks());
	    }

	    @DeleteMapping("/deleteById/{id}")
	    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
	        bookService.deleteBook(id);
	        return ResponseEntity.ok("Book deleted successfully!");
	    }
	


  
}