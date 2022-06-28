package com.bsuir.birukov.controller;

import com.bsuir.birukov.entity.Category;
import com.bsuir.birukov.entity.Product;
import com.bsuir.birukov.repository.CategoryRepository;
import com.bsuir.birukov.repository.ProductRepository;
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
@RequestMapping("/Category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductController productController;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<Category>> getAllCategories()  {
        log.info("Start Get Category By ID Method");
        List<Category> objectsData;
        objectsData = categoryRepository.findAll();
        String outString = FunctionUtil.convertListToString(objectsData);
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Category>> getCategoryById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get Category By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<Category> objectsData;
        objectsData = categoryRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<Category> addNewCategory(@RequestBody Category obj) throws SQLException, ParseException {

        log.info("Start Post Category method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        categoryRepository.save(obj);
        log.info("Category obj saved");
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<Category> updateValueInCategory(@RequestBody Category obj) throws SQLException, ParseException {

        log.info("Start Put Category method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<Category> objectsData;
        objectsData = Optional.of(categoryRepository.findById(obj.getCategory_id()).map(x -> {
            x.setCategory_id(obj.getCategory_id());
            x.setName(obj.getName());
            x.setNum_of_categories(obj.getNum_of_categories());
            x.setDescriptions(obj.getDescriptions());
            return categoryRepository.save(x);
        }).orElseGet(() -> {
            obj.setCategory_id(obj.getCategory_id());
            return categoryRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Category>> deleteValueFromCategories(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<Category> obj = categoryRepository.findById(id);
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}
