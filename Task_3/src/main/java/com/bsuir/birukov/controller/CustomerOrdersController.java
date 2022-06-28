package com.bsuir.birukov.controller;

import com.bsuir.birukov.entity.CustomerOrders;
import com.bsuir.birukov.entity.Order;
import com.bsuir.birukov.repository.CustomerOrdersRepository;
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
import java.util.Set;

@RestController
@RequestMapping("/CustomerOrders")
@Slf4j
public class CustomerOrdersController {
    @Autowired
    CustomerOrdersRepository customerOrdersRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<CustomerOrders>> getAllCustomerOrders()  {
        log.info("Start Get CustomerOrders By ID Method");
        List<CustomerOrders> objectsData;
        objectsData = customerOrdersRepository.findAll();
        String outString = FunctionUtil.convertListToString(objectsData);
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<CustomerOrders>> getCustomerOrderById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get CustomerOrders By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<CustomerOrders> objectsData;
        objectsData = customerOrdersRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<CustomerOrders> addNewCustomerOrder(@RequestBody CustomerOrders obj) throws SQLException, ParseException {

        log.info("Start Post CustomerOrders method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        customerOrdersRepository.save(obj);
        log.info("CustomerOrders obj saved");
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/database/{id}")
    public synchronized ResponseEntity<String> addOrderToCustOrder(@PathVariable(value = "id") String notConvertedCustOrdID,
                                                                   @RequestBody String notConvertedOrdID){
        log.info("Start add order to customer order");
        int CustOrdID = FunctionUtil.validateID(notConvertedCustOrdID);
        log.info("custOrdID = " + CustOrdID);
        int OrdID = FunctionUtil.validateID(notConvertedOrdID);
        log.info("OrdID = " + OrdID);
        try{
            CustomerOrders obj = customerOrdersRepository.getById(CustOrdID);
            Set<Order> orderIDs = obj.getOrderIDs();
            orderIDs.add(orderRepository.getById(OrdID));
            obj.setOrderIDs(orderIDs);
            obj.setNum_of_orders(obj.getNum_of_orders()+1);
            customerOrdersRepository.save(obj);
            return ResponseEntity.ok("Order "+ OrdID +" has add to Customer orders "+ CustOrdID);
        }catch (Exception ex){
            return ResponseEntity.ok("Error. Can't add category to this product");
        }
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<CustomerOrders> updateValueInCustomerOrder(@RequestBody CustomerOrders obj) throws SQLException, ParseException {

        log.info("Start Put CustomerOrders method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<CustomerOrders> objectsData;
        objectsData = Optional.of(customerOrdersRepository.findById(obj.getCustom_order_id()).map(x -> {
            x.setCustom_order_id(obj.getCustom_order_id());
            x.setAccount_id(obj.getAccount_id());
            x.setDelivery_id(obj.getDelivery_id());
            x.setNum_of_orders(obj.getNum_of_orders());
            x.setShipping(obj.getShipping());
            x.setStatus(obj.getStatus());
            return customerOrdersRepository.save(x);
        }).orElseGet(() -> {
            obj.setCustom_order_id(obj.getCustom_order_id());
            return customerOrdersRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<CustomerOrders>> deleteValueFromCustomerOrders(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<CustomerOrders> obj = customerOrdersRepository.findById(id);
        customerOrdersRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}