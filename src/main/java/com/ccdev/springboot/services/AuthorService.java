package com.ccdev.springboot.services;

import com.ccdev.springboot.entities.Author;
import com.ccdev.springboot.entities.Editorial;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author saveAuthor(Author author);
    Optional<Author> findById(Integer id);
    Optional<Author> findByName(String name);
    List<Author> listAllAuthors();
    Author updateAuthor(Author author);
    void deleteAuthor(Integer id) throws ClassNotFoundException;
    List<Author> listByIds(List<Integer> ids);
}
