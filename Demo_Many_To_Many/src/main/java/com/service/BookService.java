package com.service;


import java.util.List;

import com.model.Book;

public interface BookService {
    Book saveBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    void deleteBook(Long id);
//       List<BookDTO> getAllBooks();
}
