package com.budiluhur.product_catalog.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double calculateDiscountedPrice(double originalPrice, double discountPercentage) {
        if (discountPercentage <= 0) {
            return originalPrice;
        }
        return originalPrice - (originalPrice * discountPercentage / 100);
    }
}
