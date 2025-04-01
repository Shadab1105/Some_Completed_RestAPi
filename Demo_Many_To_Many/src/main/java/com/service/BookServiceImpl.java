package com.service;

import org.springframework.stereotype.Service;

import com.model.Author;
import com.model.Book;
import com.model.BookDTO;
import com.repository.AuthorRepository;
import com.repository.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    // ✅ Save Book with Duplicate Handling
    @Override
    public Book saveBook(Book book) {
        Set<Author> authors = new HashSet<>();

        // ✅ Author Handling (Duplicate Avoidance)
        for (Author author : book.getAuthors()) {
            Optional<Author> existingAuthor = authorRepo.findByName(author.getName());

            if (existingAuthor.isPresent()) {    
                authors.add(existingAuthor.get());   // ID fetch (if exists)
            } else {
                authors.add(author);               // Naya record create
            }
        }

        book.setAuthors(authors);

        // ✅ Book Handling (Duplicate Avoidance)
        Optional<Book> existingBook = bookRepo.findByName(book.getName());

        if (existingBook.isPresent()) {
            return existingBook.get();     // Book already exists → return existing one
        }

        return bookRepo.save(book);        // Nahi hai to naya book create
    }

    // ✅ Fetch All Books with Authors
//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepo.findAll();
//    }

    // ✅ Fetch Book by ID with Authors
    @Override
    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    // ✅ Delete Book (and Cascade Delete from Join Table)
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        bookRepo.delete(book);
    }
    
    
//    @Override
//    public List<BookDTO> getAllBooks() {
//        List<Book> books = bookRepo.findAll();
//        return books.stream().map(book -> {
//            Set<String> authorNames = book.getAuthors()
//                    .stream()
//                    .map(author -> author.getName())
//                    .collect(Collectors.toSet());
//            
//            return new BookDTO(book.getId(), book.getName(), authorNames);
//        }).collect(Collectors.toList());
//    }
    
    public List<Book> getAllBooks() {
        return bookRepo.findAll();         // ✅ JPA method se direct entity fetch ho rahi hai
    }
}
