package com.bsuir.birukov.controller;

import com.bsuir.birukov.entity.Delivery;
import com.bsuir.birukov.repository.DeliveryRepository;
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
@RequestMapping("/Delivery")
@Slf4j
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<Delivery>> getAllDeliveries()  {
        log.info("Start Get Delivery By ID Method");
        List<Delivery> objectsData;
        objectsData = deliveryRepository.findAll();
        String outString = FunctionUtil.convertListToString(objectsData);
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Delivery>> getDeliveryById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get Delivery By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<Delivery> objectsData;
        objectsData = deliveryRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<Delivery> addNewDelivery(@RequestBody Delivery obj) throws SQLException, ParseException {

        log.info("Start Post Delivery method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        deliveryRepository.save(obj);
        log.info("Delivery obj saved");
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<Delivery> updateValueInDelivery(@RequestBody Delivery obj) throws SQLException, ParseException {

        log.info("Start Put Delivery method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<Delivery> objectsData;
        objectsData = Optional.of(deliveryRepository.findById(obj.getDelivery_id()).map(x -> {
            x.setDelivery_id(obj.getDelivery_id());
            x.setName(obj.getName());
            x.setTime_of_order(obj.getTime_of_order());
            x.setPrice(obj.getPrice());
            x.setStatus(obj.getStatus());
            return deliveryRepository.save(x);
        }).orElseGet(() -> {
            obj.setDelivery_id(obj.getDelivery_id());
            return deliveryRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Delivery>> deleteValueFromDeliveries(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<Delivery> obj = deliveryRepository.findById(id);
        deliveryRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}