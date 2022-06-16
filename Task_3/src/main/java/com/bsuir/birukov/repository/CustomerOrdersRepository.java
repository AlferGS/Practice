package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Integer> {       //Набор стандартных методов
}
