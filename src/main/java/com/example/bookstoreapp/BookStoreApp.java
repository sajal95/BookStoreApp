package com.example.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class BookStoreApp {

    public static void main(String[] args) {
//        SpringApplication.run(BookStoreApplication.class, args);
        ApplicationContext context = SpringApplication.run(BookStoreApp.class, args);
        log.info("Book Store Application started in {} Environment",
                context.getEnvironment().getProperty("environment"));
        log.info("Book Store Application DB user is {}",
                context.getEnvironment().getProperty("spring.datasource.username"));
    }
}
