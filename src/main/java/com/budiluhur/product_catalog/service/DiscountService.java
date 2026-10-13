package com.budiluhur.product_catalog.service;

import org.springframework.stereotype.Service;

/**
 * Service class untuk mengelola logika perhitungan diskon.
 * @Service → Spring Container mendaftarkan ini sebagai Spring Bean.
 */
@Service
public class DiscountService {

    /**
     * Menghitung harga setelah diskon.
     * @param originalPrice     harga asli produk
     * @param discountPercentage persentase diskon (0 = tidak ada diskon)
     * @return harga setelah dikurangi diskon
     */
    public double calculateDiscountedPrice(double originalPrice, double discountPercentage) {
        if (discountPercentage <= 0) {
            return originalPrice;
        }
        return originalPrice - (originalPrice * discountPercentage / 100);
    }
}
