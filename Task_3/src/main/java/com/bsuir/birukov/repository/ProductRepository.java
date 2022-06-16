package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {       //Набор стандартных методов
}
