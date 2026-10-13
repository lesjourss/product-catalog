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

    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {
            System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");

            // Tambah produk baru
            productService.createProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));

            // Tampilkan semua produk
            System.out.println("Daftar Produk di Spring Container:");
            productService.getAllProducts().forEach(System.out::println);

            // --- Info Harga & Diskon ---
            System.out.println("\n--- INFORMASI HARGA & DISKON ---");

            // PRD-01: diskon 10%
            double diskon1 = 10.0;
            double hargaAkhir1 = productService.getDiscountedProductPrice("PRD-01", diskon1);
            Product p1 = productService.getProductById("PRD-01");
            System.out.printf("[%s] %s%n", p1.getId(), p1.getName());
            System.out.printf("- Harga Normal : Rp%,.0f%n", p1.getPrice());
            System.out.printf("- Diskon       : %.1f%% (%s)%n", diskon1, diskon1 > 0 ? "Ada Diskon" : "Tidak Ada Diskon");
            System.out.printf("- Harga Akhir  : Rp%,.0f%n", hargaAkhir1);

            // PRD-02: tidak ada diskon
            double diskon2 = 0;
            double hargaAkhir2 = productService.getDiscountedProductPrice("PRD-02", diskon2);
            Product p2 = productService.getProductById("PRD-02");
            System.out.printf("[%s] %s%n", p2.getId(), p2.getName());
            System.out.printf("- Harga Normal : Rp%,.0f%n", p2.getPrice());
            System.out.printf("- Diskon       : %.0f%% (%s)%n", diskon2, diskon2 > 0 ? "Ada Diskon" : "Tidak Ada Diskon");
            System.out.printf("- Harga Akhir  : Rp%,.0f%n", hargaAkhir2);
        };
    }
}
