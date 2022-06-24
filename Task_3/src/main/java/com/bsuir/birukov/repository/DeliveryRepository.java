package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {       //Набор стандартных методов
    Optional<Delivery> findById (Integer id);
    List<Delivery> findAll ();
}
