package com.ccdev.springboot.services;

import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {
    Book saveBook(Book book);
    Optional<Book> findById(Integer id);
    Optional<Book> findByTitle(String title);
    List<Book> listAllBooks();
    Book updateBook(Book book);
    void deleteBook(Integer id);
    List<Book> findByCategory(Category category);
}
