package com.budiluhur.product_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.budiluhur.product_catalog.exception.ProductNotFoundException;
import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.repository.ProductRepository;

/**
 * Service class yang berisi business logic untuk Product (Business Logic Layer).
 * @Service → Spring Container mendaftarkan ini sebagai Spring Bean.
 *
 * Constructor Injection:
 * Spring otomatis meng-inject ProductRepository saat ProductService dibuat — tanpa keyword 'new'.
 * Field productRepository dideklarasikan final sehingga bersifat immutable.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection — Spring otomatis inject ProductRepository Bean
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        productRepository.addProduct(product);
        return product;
    }
}
