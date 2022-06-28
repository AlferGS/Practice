package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {       //Набор стандартных методов
    Optional<Order> findById(Integer id);;
    List<Order> findAll();
}
