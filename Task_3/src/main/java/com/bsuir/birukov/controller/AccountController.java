package com.bsuir.birukov.controller;

import com.bsuir.birukov.entity.Account;
import com.bsuir.birukov.repository.AccountRepository;
import com.bsuir.birukov.util.FunctionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Account")
@Slf4j
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<Account>> getAllAccounts()  {
        log.info("Start Get Account By ID Method");
        List<Account> objectsData;
        objectsData = accountRepository.findAll();
        String outString = FunctionUtil.convertListToString(objectsData);
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Account>> getAccountById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get Account By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<Account> objectsData;
        objectsData = accountRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<Account> addNewAccount(@RequestBody Account obj) throws SQLException, ParseException {

        log.info("Start Post Acc method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        accountRepository.save(obj);
        log.info("Account obj saved");
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<Account> updateValueInAccount(@RequestBody Account obj) throws SQLException, ParseException {

        log.info("Start Put Account method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<Account> objectsData;
        objectsData = Optional.of(accountRepository.findById(obj.getAccount_id()).map(x -> {
            x.setAccount_id(obj.getAccount_id());
            x.setFull_name(obj.getFull_name());
            x.setEmail(obj.getEmail());
            x.setPassword(obj.getPassword());
            x.setPhone_number(obj.getPhone_number());
            x.setAddress(obj.getAddress());
            return accountRepository.save(x);
        }).orElseGet(() -> {
            obj.setAccount_id(obj.getAccount_id());
            return accountRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Account>> deleteValueFromAccounts(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<Account> obj = accountRepository.findById(id);
        accountRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}
