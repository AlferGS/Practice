package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {       //Набор стандартных методов
}
