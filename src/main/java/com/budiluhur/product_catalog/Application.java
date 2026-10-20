package com.budiluhur.product_catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.repository.ProductRepository;

/**
 * Main class Spring Boot Application.
 * @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 * Harus di root package agar semua Bean dalam sub-package terdeteksi.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(ProductRepository repository) {
        return args -> {
            repository.save(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
            repository.save(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
            System.out.println("Data awal berhasil disimpan!");
        };
    }
}
