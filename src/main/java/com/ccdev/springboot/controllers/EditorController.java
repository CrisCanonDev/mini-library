package com.ccdev.springboot.controllers;

import com.ccdev.springboot.entities.Editorial;
import com.ccdev.springboot.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editors")
public class EditorController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/new")
    public String showFormNewEditorial(Model model){
        model.addAttribute("editorial", new Editorial());
        return "editorial/form_editorial";
    }

    @PostMapping("/save")
    public String saveEditorial(@ModelAttribute Editorial editorial){
        editorialService.saveEditorial(editorial);
        return "redirect:/editors/list";
    }
    @GetMapping({"list","/"})
    public String listEditorial(Model model){
        List<Editorial> editorials = editorialService.listAllEditorials();
        model.addAttribute("editorials", editorials);
        return "editorial/list_editorials";
    }

    @GetMapping("{id}")
    public String showEditorial(@PathVariable Integer id, Model model){
        Optional<Editorial> editorialOptional = editorialService.findById(id);
        if(editorialOptional.isPresent()){
            Editorial editorial = editorialOptional.get();
            model.addAttribute("editorial", editorial);
            model.addAttribute("books", editorial.getBooks());
        }
        return "editorial/show_editorial";
    }

    @GetMapping("{id}/edit")
    public String showFormEditForm(@PathVariable Integer id, Model model){
        Optional<Editorial> editorial = editorialService.findById(id);
        editorial.ifPresent(value -> model.addAttribute("editorial",value));
        return "editorial/form_editorial";
    }
    @GetMapping("{id}/update")
    public String updateEditorial(@PathVariable Integer id, @ModelAttribute Editorial editorial){
        Optional<Editorial> editorialOptional = editorialService.findById(id);
        if(editorialOptional.isPresent()){
            Editorial currentEditorial = editorialOptional.get();
            currentEditorial.setName(editorial.getName());
            editorialService.updateEditorial(currentEditorial);
        }
        return "redirect:/editors/list";
    }

    @GetMapping("{id}/delete")
    public String deleteEditorial(@PathVariable Integer id){
        editorialService.deleteEditorial(id);
        return "redirect:/editors/list";
    }


    @GetMapping("{id}/books")
    public String showBooksOfEditorial(@PathVariable Integer id, Model model){
        Optional<Editorial> optionalEditorial = editorialService.findById(id);
        if(optionalEditorial.isPresent()){
            Editorial editorial =  optionalEditorial.get();
            model.addAttribute("editorial", editorial);
            model.addAttribute("books", editorial.getBooks());
        }
        return "editorial/show_books_editorial";
    }

}
