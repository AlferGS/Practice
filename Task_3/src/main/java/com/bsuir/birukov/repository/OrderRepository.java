package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {       //Набор стандартных методов
    Optional<Order> findById(Integer id);
    //Optional<Order> findByProductId(Integer id);
    List<Order> findAll();
}
