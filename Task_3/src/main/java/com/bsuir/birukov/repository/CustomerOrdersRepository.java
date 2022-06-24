package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.entity.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Integer> {       //Набор стандартных методов
    Optional<CustomerOrders> findById (Integer id);
    List<CustomerOrders> findAll ();
}
