package com.budiluhur.product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budiluhur.product_catalog.model.Product;

/**
 * Repository interface untuk mengelola akses data Product via Spring Data JPA.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    // Mini Challenge: Derived query method untuk pencarian berdasarkan nama (LIKE %keyword% ignore case)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}

