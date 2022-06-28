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
import java.util.Set;

@RestController
@RequestMapping("/Product")
@Slf4j
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/database/")
    public synchronized ResponseEntity<List<Product>> getAllProducts()  {
        log.info("Start Get Product By ID Method");
        List<Product> objectsData;
        objectsData = productRepository.findAll();
        return ResponseEntity.ok(objectsData);
    }

    @GetMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Product>> getProductsById(@PathVariable(value = "id") String notConvertedID) {
        log.info("Start Get Product By ID Method");
        log.info("notConvertedID - " + notConvertedID);
        Optional<Product> objectsData;
        objectsData = productRepository.findById(FunctionUtil.validateID(notConvertedID));
        log.info("objectsData - " + objectsData.toString());
        if(objectsData.isPresent())
            return ResponseEntity.ok(objectsData);
        return  ResponseEntity.ok(null);
    }

    @PostMapping("/database/")
    public synchronized ResponseEntity<Product> addNewProducts(@RequestBody Product obj) throws SQLException, ParseException {

        log.info("Start Post Product method");
        if (!obj.notNull()) {
            return ResponseEntity.ok(null);
        }
        productRepository.save(obj);
        log.info("Product obj saved");
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/database/{id}")
    public synchronized ResponseEntity<String> addCategoryToProduct(@PathVariable(value = "id") String notConvertedProdID,
                                                                    @RequestBody String notConvertedCatID){
        log.info("Start add Category To Product");
        int ProdID = FunctionUtil.validateID(notConvertedProdID);
        log.info("ProdID = " + ProdID);
        String substring = notConvertedCatID.substring(notConvertedCatID.indexOf(":",0) + 2,
                notConvertedCatID.indexOf("}", 0) -2);
        log.info("substring = " + substring);
        int CatID = FunctionUtil.validateID(substring);
        log.info("CatID = " + CatID);
        try{
            Product obj = productRepository.getById(ProdID);
            Set<Category> categoryIDs = obj.getCategoryIDs();
            categoryIDs.add(categoryRepository.getById(CatID));
            obj.setCategoryIDs(categoryIDs);
            productRepository.save(obj);
            return ResponseEntity.ok("Category "+ CatID +" has add to Product "+ ProdID);
        } catch (Exception ex){
            return ResponseEntity.ok("Error. Can't add category to this product");
        }
    }

    @PutMapping("/database/")
    public synchronized ResponseEntity<Product> updateValueInProducts(@RequestBody Product obj) throws SQLException, ParseException {

        log.info("Start Put Product method");
        if(!obj.notNull()){
            return ResponseEntity.ok(null);
        }
        Optional<Product> objectsData;
        objectsData = Optional.of(productRepository.findById(obj.getProduct_id()).map(x -> {
            x.setProduct_id(obj.getProduct_id());
            x.setName(obj.getName());
            x.setManufactorer(obj.getManufactorer());
            x.setWidth(obj.getWidth());
            x.setHeight(obj.getHeight());
            x.setWeight(obj.getWeight());
            x.setPrice(obj.getPrice());
            return productRepository.save(x);
        }).orElseGet(() -> {
            obj.setProduct_id(obj.getProduct_id());
            return productRepository.save(obj);
        }));
        return ResponseEntity.ok(obj);
    }

//    public synchronized void delCategoryInProduct(Integer notConvertedProdID){
//        log.info("Start Del Category In Product");
//        int CatID = notConvertedProdID;
//        Category obj = categoryRepository.getById(CatID);
//        obj.setProductIDs(obj.delProductIDsById(obj.getProductIDs(), CatID));
//        categoryRepository.save(obj);
//    }

    @DeleteMapping("/database/{id}")
    public synchronized ResponseEntity<Optional<Product>> deleteValueFromProducts(@PathVariable(value = "id") String notConvertedID) {
        int id = FunctionUtil.validateID(notConvertedID);
        Optional<Product> obj = productRepository.findById(id);
        productRepository.deleteById(id);
        return ResponseEntity.ok(obj);
    }
}
