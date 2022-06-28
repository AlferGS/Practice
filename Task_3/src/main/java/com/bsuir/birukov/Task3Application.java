package com.bsuir.birukov;

import com.bsuir.birukov.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task3Application {
	@Autowired
	private static AccountController accountController;
	@Autowired
	private static CategoryController categoryController;
	@Autowired
	private static CustomerOrdersController customerOrdersController;
	@Autowired
	private static DeliveryController deliveryController;
	@Autowired
	private static OrderController orderController;
	@Autowired
	private static ProductController productController;
	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}
}
