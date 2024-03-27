package com.ccdev.springboot.controllers;

import com.ccdev.springboot.entities.Author;
import com.ccdev.springboot.entities.Book;
import com.ccdev.springboot.entities.Category;
import com.ccdev.springboot.entities.Editorial;
import com.ccdev.springboot.services.AuthorService;
import com.ccdev.springboot.services.BookService;
import com.ccdev.springboot.services.CategoryService;
import com.ccdev.springboot.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private BookService bookService;

    @GetMapping({"","/list"})
    public String ListBooks(Model model){
        List<Book> books = bookService.listAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/new")
    public String ShowFormNewBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("editorials", editorialService.listAllEditorials());
        model.addAttribute("editor", editorialService.listAllEditorials());
        return "book/form_book";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book,
                           @RequestParam("editorialId") Integer editorialId,
                           @RequestParam("categoryId") Integer categoryId,
                           @RequestParam("authorsId") List<Integer> authorsId ){
        //Obtain and assign editorial / category for book
        Optional<Category> category = categoryService.findById(categoryId);
        category.ifPresent(book::setCategory);

        Optional<Editorial> editorial = editorialService.findById(editorialId);
        editorial.ifPresent(book::setEditorial);

        List<Author> authors = authorService.findByIds(authorsId);
        book.setAuthors(authors);

        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("{id}/edit")
    public String showFormEditBook(@PathVariable Integer id, Model model){
        Optional<Book> book = bookService.findById(id);

        if(book.isPresent()){
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.listAllCategories());
            model.addAttribute("editorials", editorialService.listAllEditorials());
            model.addAttribute("editor", editorialService.listAllEditorials());
        }
        return "book/form_book";
    }

    @GetMapping("{id}/update")
    public String updateBook(@PathVariable Integer id,
                             @ModelAttribute Book book,
                             @RequestParam("editorialId") Integer editorialId,
                             @RequestParam("categoryId") Integer categoryId,
                             @RequestParam("authorsId") List<Integer> authorsId){
        Optional<Editorial> editorial  = editorialService.findById(editorialId);
        editorial.ifPresent(book::setEditorial);

        Optional<Category> category  = categoryService.findById(categoryId);
        category.ifPresent(book::setCategory);

        List<Author> authors = authorService.findByIds(authorsId);
        book.setAuthors(new ArrayList<>(authors));

        book.setId(id); //Indicates that action is performed for updating rather than creating book
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("{id}/authors")
    public String showBookAuthors(@PathVariable Integer id, Model model){
        Optional<Book> optionalBook = bookService.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            model.addAttribute("authors",book.getAuthors());
        }
        return "book/show_book_authors";
    }

    @GetMapping("{id}/delete")
    public String deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return "redirect:/books/list";
    }
}
