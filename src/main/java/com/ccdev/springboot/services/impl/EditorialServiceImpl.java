package com.ccdev.springboot.services.impl;

import com.ccdev.springboot.entities.Editorial;
import com.ccdev.springboot.repositories.EditorialRepository;
import com.ccdev.springboot.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceImpl implements EditorialService {
    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public Editorial saveEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Optional<Editorial> saveById(Integer id) {
        return editorialRepository.findById(id);
    }

    @Override
    public Optional<Editorial> findByName(String name) {
        return editorialRepository.findByName(name);
    }

    @Override
    public List<Editorial> listAllEditorials() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial updateEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public void deleteEditorial(Integer id) {
        editorialRepository.deleteById(id);
    }
}
