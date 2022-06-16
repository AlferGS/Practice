package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {       //Набор стандартных методов
}
