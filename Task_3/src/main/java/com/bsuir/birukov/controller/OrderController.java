package com.bsuir.birukov.controller;

import com.bsuir.birukov.entity.Order;
import com.bsuir.birukov.repository.OrderRepository;
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
@RequestMapping("/Order")
@Slf4j
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<Order>> getAllOrders()  {
        log.info("Start Get Order By ID Method");
        List<Order> objectsData;
        objectsData = orderRepository.findAll();
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Order>> getOrderById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get Order By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<Order> objectsData;
        objectsData = orderRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<Order> addNewOrder(@RequestBody Order obj) throws SQLException, ParseException {

        log.info("Start Post Order method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        orderRepository.save(obj);
        log.info("Order obj saved");
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<Order> updateValueInOrder(@RequestBody Order obj) throws SQLException, ParseException {

        log.info("Start Put Order method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<Order> objectsData;
        objectsData = Optional.of(orderRepository.findById(obj.getOrder_id()).map(x -> {
            x.setOrder_id(obj.getOrder_id());
            x.setProduct_id(obj.getProduct_id());
            x.setName(obj.getName());
            x.setDate_time(obj.getDate_time());
            x.setNotes(obj.getNotes());
            x.setQuantity(obj.getQuantity());
            x.setPrice(obj.getPrice());
            return orderRepository.save(x);
        }).orElseGet(() -> {
            obj.setOrder_id(obj.getOrder_id());
            return orderRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Order>> deleteValueFromOrders(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<Order> obj = orderRepository.findById(id);
        orderRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}
