package com.budiluhur.product_catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.service.ProductService;

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

    /**
     * Runner untuk menguji bahwa Dependency Injection bekerja.
     * Spring otomatis meng-inject ProductService Bean ke method ini.
     */
    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {
            System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");

            // Tambah produk baru
            productService.createProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));

            // Tampilkan semua produk
            System.out.println("Daftar Produk di Spring Container:");
            productService.getAllProducts().forEach(System.out::println);
        };
    }
}
