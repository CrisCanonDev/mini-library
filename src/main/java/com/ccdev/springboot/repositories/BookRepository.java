package com.ccdev.springboot.repositories;

import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.entities.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    List<Book> findByCategory(Category category);
}
