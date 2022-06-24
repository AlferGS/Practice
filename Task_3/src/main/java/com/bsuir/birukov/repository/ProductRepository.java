package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {       //Набор стандартных методов
    Optional<Product> findById(Integer id);
    List<Product> findAll();
}
