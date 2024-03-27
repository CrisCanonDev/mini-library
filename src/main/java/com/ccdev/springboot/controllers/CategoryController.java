package com.ccdev.springboot.controllers;

import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.entities.Category;
import com.ccdev.springboot.services.BookService;
import com.ccdev.springboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class    CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;

    @GetMapping({"/","/list"})
    public String listCategories(Model model){
        List<Category> categories = categoryService.listAllCategories();
        model.addAttribute("categories", categories);
        return "categories/list";
    }
    @GetMapping("/new")
    public String showFormNewCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/form_category";
    }
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category){
        Category categorySaved = categoryService.saveCategory(category);
        //Once saving a new category, a new books list is created (Should be empty) to be linked to that category
        List<Book> books = bookService.findByCategory(categorySaved);
        categorySaved.setBooks(books);
        return "redirect:/categories/list";
    }
    @GetMapping("/edit/{id}")
    public String showFormEditCategory(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryService.findById(id);
        category.ifPresent(value -> model.addAttribute("category", category));
        return "category/form_category";
    }
    @PostMapping("/{id}/update")
    public String updateCategory(@PathVariable Integer id, @ModelAttribute Category category){
        Category currentCategory = categoryService.findById(id).orElse(null);

        if(currentCategory != null){
            category.setBooks(currentCategory.getBooks());
            categoryService.updateCategory(category);
        }
        return "redirect:/categories/list";
    }
    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/categories/list";
    }
}
