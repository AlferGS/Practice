package com.bsuir.birukov.repository;

import com.bsuir.birukov.entity.CustomOrdersHasOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomOrdersHasOrderRepository extends JpaRepository<CustomOrdersHasOrder, Integer> {       //Набор стандартных методов
}
