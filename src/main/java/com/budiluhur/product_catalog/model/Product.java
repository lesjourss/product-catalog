package com.budiluhur.product_catalog.model;

/**
 * Model class yang merepresentasikan data Produk.
 */
public class Product {

    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("[%s] %s - Rp%.1f", id, name, price);
    }
}
