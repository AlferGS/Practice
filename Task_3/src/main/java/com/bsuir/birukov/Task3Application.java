package com.bsuir.birukov;

import com.bsuir.birukov.controller.RESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task3Application {
	@Autowired
	private static RESTController restController;
	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
	}
}
