package com.ccdev.springboot.services.impl;

import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.entities.Category;
import com.ccdev.springboot.repositories.BookRepository;
import com.ccdev.springboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }
}
