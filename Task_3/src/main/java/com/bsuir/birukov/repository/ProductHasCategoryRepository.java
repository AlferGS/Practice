package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductHasCategoryRepository extends JpaRepository<Category, Product>{       //Набор стандартных методов
    Optional<Category> findById(Category category);
}