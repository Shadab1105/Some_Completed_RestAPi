package com.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.Author;
import com.model.Book;
import com.repository.AuthorRepository;
import com.repository.BookRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookServiceImpl implements BookService
{
	
	@Autowired
	private BookRepository br;

	@Autowired
	private AuthorRepository ar;
\

	public Book saveBookData(Book b) 
	{
     Optional<Book> existingBook = br.findByBName(b.getBName());

        if (existingBook.isPresent()) {
            // Agar book already exist karti hai, toh naye authors add karo
            Book bookToUpdate = existingBook.get();
            for (Author author : b.getAuthors()) {
                Optional<Author> existingAuthor = ar.findByAName(author.getAName());
                existingAuthor.ifPresentOrElse(bookToUpdate.getAuthors()::add, () -> bookToUpdate.getAuthors().add(author));
            }
            return br.save(bookToUpdate);
        } else {
            // Agar book exist nahi karti toh naye book aur author insert karo
            Set<Author> updatedAuthors = new HashSet<>();
            for (Author author : b.getAuthors()) {
                Optional<Author> existingAuthor = ar.findByAName(author.getAName());
                updatedAuthors.add(existingAuthor.orElse(author));
            }
            b.setAuthors(updatedAuthors);
            return br.save(b);
        }
    }

	public ResponseEntity<?> findById(Long id) throws ResourceNotFoundException 
	{
		Book BookDataById = br.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not available id= "+id));
		return ResponseEntity.ok().body(BookDataById);
	}


	@Transactional
	public List<?> findAll() 
	{
		List<?> all = br.findAll();
		if(all==null)
		{
			return List.of(Map.of("Message","DataBase is Empty"));
		}
		return all;
	}



	@Override
	public ResponseEntity<?> updateBookData(Book b,Long id) throws ResourceNotFoundException 
	{
		Book byId = br.findById(id).orElseThrow(()->new ResourceNotFoundException("Id Not available = "+id));
		
		if(byId!=null)
		{
			byId.setBName(b.getBName());
			byId.setPublishDate(b.getPublishDate());
		}
		
		br.save(byId);
		return ResponseEntity.ok(Map.of("Data Successfully Update",b));
	}

	@Override
	public ResponseEntity<?> deleteBookData(Long id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

	



//	public ResponseEntity<?> deleteBookData(Long id) throws ResourceNotFoundException 
//	{
//		Book byId = br.findById(id).orElseThrow(()->new ResourceNotFoundException("Id Not available = "+id));
//		if(byId!=null)
//		{
//			br.deleteById(byId.getBId());
//			return ResponseEntity.ok().body("Successfully Deleted");
//		}
//		else
//		{
//			return ResponseEntity.ok().body("Not Deleted");	
//		}
//		
//	}


	
}
