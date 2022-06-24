package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {       //Набор стандартных методов
    Optional<Category> findById (Integer id);    //Optional
    List<Category> findAll();
}
