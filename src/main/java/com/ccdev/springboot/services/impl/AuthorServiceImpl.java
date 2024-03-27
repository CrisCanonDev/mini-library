package com.ccdev.springboot.services.impl;

import com.ccdev.springboot.entities.Author;
import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.repositories.AuthorRepository;
import com.ccdev.springboot.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer id) throws ClassNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            deleteAuthorRelations(author);
            authorRepository.deleteById(id);
        }else{
            throw  new ClassNotFoundException("Error");
        }
    }

    @Override
    public List<Author> listByIds(List<Integer> ids) {
        return authorRepository.findAllById(ids);
    }

    private void deleteAuthorRelations(Author author){
        for(Book book: author.getBooks()){
            book.getAuthors().remove(author);
        }
        author.getBooks().clear();
    }
}
