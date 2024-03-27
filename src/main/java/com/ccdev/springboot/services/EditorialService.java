package com.ccdev.springboot.services;

import com.ccdev.springboot.entities.Editorial;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EditorialService {
    Editorial saveEditorial(Editorial editorial);
    Optional<Editorial> findById(Integer id);
    Optional<Editorial> saveById(Integer id);
    Optional<Editorial> findByName(String name);
    List<Editorial> listAllEditorials();
    Editorial updateEditorial(Editorial editorial);
    void deleteEditorial(Integer id);
}
