package com.ccdev.springboot.controllers;


import com.ccdev.springboot.entities.Author;
import com.ccdev.springboot.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/authors"})
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping({"/","/list"})
    public String listAuthors(Model model){
        List<Author> authors = authorService.listAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/list_authors";
    }
    @GetMapping("/new")
    public String showFormNewAuthor(Model model){
        model.addAttribute("author", new Author());
        return "author/form_author";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author){
        authorService.saveAuthor(author);
        return "redirect:/authors/list";
    }
    @GetMapping("/edit/{id}")
    public String showFormEditAuthor(@PathVariable Integer id, Model model){
        Optional<Author> author = authorService.findById(id);
        author.ifPresent(value -> model.addAttribute("author",value));
        return "author/form_author";
    }

    @GetMapping("delete/{id}")
    public String deleteAuthor(@PathVariable Integer id) throws ClassNotFoundException {
        authorService.deleteAuthor(id);
        return "redirect:/authors/list";
    }
}
