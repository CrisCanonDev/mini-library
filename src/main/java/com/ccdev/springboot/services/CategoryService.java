package com.ccdev.springboot.services;

import com.ccdev.springboot.entities.Category;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    Optional<Category> findById(Integer id);
    Optional<Category> findByName(String name);
    List<Category> listAllCategories();
    Category updateCategory(Category category);
    void deleteCategory(Integer id);
}
