package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductByID(int productID);
    Product getProductByName(String productName);
    void addProduct(Product product);
}
