package com.bsuir.birukov.repository;

import java.util.List;
import java.util.Optional;

import com.bsuir.birukov.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {       //Набор стандартных методов
    Optional<Account> findById (Integer id);    //Optional
    List<Account> findAll();
}
