package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHasCategoryRepository extends JpaRepository<ProductHasCategory, Integer>{       //Набор стандартных методов
}