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
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produk dengan ID '" + id + "' tidak ditemukan!"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) throws ProductNotFoundException {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) throws ProductNotFoundException {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    /**
     * Menghitung harga diskon produk berdasarkan ID.
     * @param id       ID produk
     * @param discount persentase diskon (0 = tidak ada diskon)
     * @return harga setelah diskon
     */
    public double getDiscountedProductPrice(String id, double discount) throws ProductNotFoundException {
        Product product = getProductById(id);
        return discountService.calculateDiscountedPrice(product.getPrice(), discount);
    }
}
