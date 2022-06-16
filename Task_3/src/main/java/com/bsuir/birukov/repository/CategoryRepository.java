package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {       //Набор стандартных методов
}
