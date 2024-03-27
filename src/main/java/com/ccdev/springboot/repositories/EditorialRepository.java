package com.ccdev.springboot.repositories;

import com.ccdev.springboot.entities.Editorial;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
    Optional<Editorial> findByName(String name);
}
