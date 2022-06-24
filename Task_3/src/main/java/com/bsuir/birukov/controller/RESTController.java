package com.bsuir.birukov.controller;


import lombok.extern.slf4j.Slf4j;
import com.bsuir.birukov.entity.*;
import com.bsuir.birukov.repository.*;
import com.bsuir.birukov.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/ctrl")
@Slf4j
public class RESTController {
    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public CustomerOrdersRepository custOrdRepository;

    @Autowired
    public CustomOrdersHasOrderRepository custOrdHasOrdRepository;

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ProductHasCategoryRepository prodHasCatRepository;

    @Autowired
    public ProductRepository prodRepository;

    public Account account = new Account();
    public Category category = new Category();
    public CustomerOrders customerOrders = new CustomerOrders();
    public Delivery delivery = new Delivery();
    public Order order = new Order();
    public Product product = new Product();

    @GetMapping("/database/{className}/{id}")
    public synchronized ResponseEntity<String> getObjectsById(@PathVariable(value = "className") String className,
                                                              @PathVariable(value = "id") String notConvertedID)  {
        log.info("Start Get  By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        log.info("className - " + className);

        if(Objects.equals(className, account.getClass().getSimpleName())){
            Optional<Account> objectsData;
            objectsData = accountRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return  ResponseEntity.ok("Null");
        }

        if(Objects.equals(className, category.getClass().getSimpleName())){
            Optional<Category> objectsData;
            objectsData = categoryRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return ResponseEntity.ok("Null");
        }

        if(Objects.equals(className, customerOrders.getClass().getSimpleName())){
            Optional<CustomerOrders> objectsData;
            objectsData = custOrdRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return ResponseEntity.ok("Null");
        }

        if(Objects.equals(className, delivery.getClass().getSimpleName())){
            Optional<Delivery> objectsData;
            objectsData = deliveryRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return ResponseEntity.ok("Null");
        }

        if(Objects.equals(className, order.getClass().getSimpleName())){
            Optional<Order> objectsData;
            objectsData = orderRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return ResponseEntity.ok("Null");
        }

        if(Objects.equals(className, product.getClass().getSimpleName())){
            Optional<Product> objectsData;
            objectsData = prodRepository.findById(FunctionUtil.validateID(notConvertedID));
            log.info("objectsData - " + objectsData.toString());
            if(objectsData.isPresent())
                return ResponseEntity.ok(objectsData.toString());
            return ResponseEntity.ok("Null");
        }

        return ResponseEntity.ok("Class aren't exist"); // ok or notFound
    }


    @GetMapping("/database")
    public synchronized ResponseEntity<String> getAllValuesFromTable(@RequestParam String nameOfTable){

        log.info("Start Get All Method");

        if(Objects.equals(nameOfTable, account.getClass().getSimpleName())){
            List<Account> objectsData;
            objectsData = accountRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        if(Objects.equals(nameOfTable, category.getClass().getSimpleName())){
            List<Category> objectsData;
            objectsData = categoryRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        if(Objects.equals(nameOfTable, customerOrders.getClass().getSimpleName())){
            List<CustomerOrders> objectsData;
            objectsData = custOrdRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        if(Objects.equals(nameOfTable, delivery.getClass().getSimpleName())){
            List<Delivery> objectsData;
            objectsData = deliveryRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        if(Objects.equals(nameOfTable, order.getClass().getSimpleName())){
            List<Order> objectsData;
            objectsData = orderRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        if(Objects.equals(nameOfTable, product.getClass().getSimpleName())){
            List<Product> objectsData;
            objectsData =  prodRepository.findAll();
            String outString = FunctionUtil.convertListToString(objectsData);
            return ResponseEntity.ok(outString);
        }

        return ResponseEntity.ok("Class aren't exist");
    }

    @PostMapping("/database/{className}/")
    public synchronized ResponseEntity<String> addNewValue(@PathVariable(value = "className") String className,
                                                           @RequestBody Object object) throws SQLException, ParseException {

        log.info("Start Post method");
        log.info("className - " + className);
        log.info("category.toString() - " + category.getClass().getSimpleName());
        log.info("object.class - " + object.getClass().getSimpleName());
        log.info("object - " + object);

        if(Objects.equals(className, account.getClass().getSimpleName())){
            log.info("it's account");

            Account obj = new Account(object);
            log.info("account created");
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            accountRepository.save(obj);
            log.info("account obj saved");
            return ResponseEntity.ok(obj.toString());
        }

        if(Objects.equals(className, category.getClass().getSimpleName())){
            log.info("it's category");

            Category obj = new Category(object);
            log.info("category created");
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value is Null");
            }
            categoryRepository.save(obj);
            log.info("category obj saved");
            return ResponseEntity.ok(obj.toString());
        }

        if(Objects.equals(className, customerOrders.getClass().getSimpleName())){
            CustomerOrders obj = new CustomerOrders(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value is Null");
            }
            custOrdRepository.save(obj);
            return ResponseEntity.ok(obj.toString());
        }

        if(Objects.equals(className, delivery.getClass().getSimpleName())){
            Delivery obj = new Delivery(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value is Null");
            }
            deliveryRepository.save(obj);
            return ResponseEntity.ok(obj.toString());
        }

        if(Objects.equals(className, order.getClass().getSimpleName())){
            Order obj = new Order(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value is Null");
            }
            orderRepository.save(obj);
            return ResponseEntity.ok(obj.toString());
        }

        if(Objects.equals(className, product.getClass().getSimpleName())){
            Product obj = new Product(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value is Null");
            }
            prodRepository.save(obj);
            return ResponseEntity.ok(obj.toString());
        }

        return ResponseEntity.ok("Class aren't exist");
    }

    @PostMapping("/database/Product/{id}/Category")
    public synchronized ResponseEntity<String> addCategoryToProduct(@PathVariable(value = "id") String notConvertedProdID,
                                                                    @RequestBody String notConvertedCatID){
        int ProdID = FunctionUtil.validateID(notConvertedProdID);
        int CatID = FunctionUtil.validateID(notConvertedCatID);
//        prodHasCatRepository.save(ProdID, CatID);

        return ResponseEntity.ok("Error. Can't add category to this product");
    }


    @PutMapping("/database/{className}/")
    public synchronized ResponseEntity<String> updateValueInTable(@PathVariable(value = "className") String className,
                                                                  @RequestBody Object object) throws SQLException, ParseException {

        log.info("Start Put method");
        log.info("className - " + className);
        log.info("object.class - " + object.getClass().getSimpleName());

        if(Objects.equals(className, account.getClass().getSimpleName())){
            Account obj = new Account(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<Account> objectsData;
            objectsData = Optional.of(accountRepository.findById(obj.getAccount_id())
                    .map(x -> {
                        x.setAccount_id(obj.getAccount_id());
                        x.setFull_name(obj.getFull_name());
                        x.setEmail(obj.getEmail());
                        x.setPassword(obj.getPassword());
                        x.setPhone_number(obj.getPhone_number());
                        x.setAddress(obj.getAddress());
                        return accountRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setAccount_id(obj.getAccount_id());
                        return accountRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        if(Objects.equals(className, category.getClass().getSimpleName())){
            Category obj = new Category(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<Category> objectsData;
            objectsData = Optional.of(categoryRepository.findById(obj.getCategory_id())
                    .map(x -> {
                        x.setCategory_id(obj.getCategory_id());
                        x.setName(obj.getName());
                        x.setNum_of_categories(obj.getNum_of_categories());
                        x.setDescriptions(obj.getDescriptions());
                        return categoryRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setCategory_id(obj.getCategory_id());
                        return categoryRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        if(Objects.equals(className, customerOrders.getClass().getSimpleName())){
            CustomerOrders obj = new CustomerOrders(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<CustomerOrders> objectsData;
            objectsData = Optional.of(custOrdRepository.findById(obj.getCustom_order_id())
                    .map(x -> {
                        x.setCustom_order_id(obj.getCustom_order_id());
                        x.setAccount_id(obj.getAccount_id());
                        x.setDelivery_id(obj.getDelivery_id());
                        x.setNum_of_orders(obj.getNum_of_orders());
                        x.setShipping(obj.getShipping());
                        x.setStatus(obj.getStatus());
                        return custOrdRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setCustom_order_id(obj.getCustom_order_id());
                        return custOrdRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        if(Objects.equals(className, delivery.getClass().getSimpleName())){
            Delivery obj = new Delivery(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<Delivery> objectsData;
            objectsData = Optional.of(deliveryRepository.findById(obj.getDelivery_id())
                    .map(x -> {
                        x.setDelivery_id(obj.getDelivery_id());
                        x.setName(obj.getName());
                        x.setTime_of_order(obj.getTime_of_order());
                        x.setPrice(obj.getPrice());
                        x.setStatus(obj.getStatus());
                        return deliveryRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setDelivery_id(obj.getDelivery_id());
                        return deliveryRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        if(Objects.equals(className, order.getClass().getSimpleName())){
            Order obj = new Order(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<Order> objectsData;
            objectsData = Optional.of(orderRepository.findById(obj.getOrder_id())
                    .map(x -> {
                        x.setOrder_id(obj.getOrder_id());
                        x.setProduct_id(obj.getProduct_id());
                        x.setName(obj.getName());
                        x.setDate_time(obj.getDate_time());
                        x.setNotes(obj.getNotes());
                        x.setQuantity(obj.getQuantity());
                        x.setPrice(obj.getPrice());
                        return orderRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setOrder_id(obj.getOrder_id());
                        return orderRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        if(Objects.equals(className, product.getClass().getSimpleName())){
            Product obj = new Product(object);
            if(!obj.notNull()){
                return ResponseEntity.ok("Some value in object is Null");
            }
            Optional<Product> objectsData;
            objectsData = Optional.of(prodRepository.findById(obj.getProduct_id())
                    .map(x -> {
                        x.setProduct_id(obj.getProduct_id());
                        x.setName(obj.getName());
                        x.setManufactorer(obj.getManufactorer());
                        x.setWidth(obj.getWidth());
                        x.setHeight(obj.getHeight());
                        x.setWeight(obj.getWeight());
                        x.setPrice(obj.getPrice());
                        return prodRepository.save(x);
                    })
                    .orElseGet(() -> {
                        obj.setProduct_id(obj.getProduct_id());
                        return prodRepository.save(obj);
                    }));
            log.info("objectsData - " + objectsData);
            return ResponseEntity.ok(objectsData.toString());
        }

        return ResponseEntity.ok("Class aren't exist");
    }

    @DeleteMapping("/database/{className}/{id}")
    public synchronized ResponseEntity<String> deleteValueFromTable(@PathVariable(value = "className") String className,
                                                                    @PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);

        if(Objects.equals(className, account.getClass().getSimpleName())){
            accountRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        if(Objects.equals(className, category.getClass().getSimpleName())){
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        if(Objects.equals(className, customerOrders.getClass().getSimpleName())){
            custOrdRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        if(Objects.equals(className, delivery.getClass().getSimpleName())){
            deliveryRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        if(Objects.equals(className, order.getClass().getSimpleName())){
            orderRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        if(Objects.equals(className, product.getClass().getSimpleName())){
            prodRepository.deleteById(id);
            return ResponseEntity.ok("delete "+id+" object in Table - " + className);
        }

        return ResponseEntity.ok("Class aren't exist");
    }
}
