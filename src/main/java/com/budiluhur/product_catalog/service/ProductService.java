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
 * Spring otomatis meng-inject ProductRepository & DiscountService tanpa keyword 'new'.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountService discountService;

    // Constructor Injection — Spring inject kedua Bean secara otomatis
    public ProductService(ProductRepository productRepository, DiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
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

    /**
     * Menghitung harga diskon produk berdasarkan ID.
     * @param id       ID produk
     * @param discount persentase diskon (0 = tidak ada diskon)
     * @return harga setelah diskon
     */
    public double getDiscountedProductPrice(String id, double discount) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        return discountService.calculateDiscountedPrice(product.getPrice(), discount);
    }
}
