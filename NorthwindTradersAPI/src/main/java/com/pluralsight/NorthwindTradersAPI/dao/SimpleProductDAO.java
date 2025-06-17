package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SimpleProductDAO implements ProductDAO {


    @Override
    public List<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 1, 5.99));
        products.add(new Product(2, "Apples", 1, 5.99));
        products.add(new Product(3, "Water", 1, 5.99));
        products.add(new Product(4, "Shoes", 2, 5.99));
        products.add(new Product(5, "Shirt", 2, 5.99));

        return products;
    }

    @Override
    public Product getProductByID(int productID) {
        List<Product> productsByID = getAllProducts();
        return productsByID.stream()
                .filter(p -> p.getProductID() == productID)
                .findFirst().orElse(null);
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }

    @Override
    public void addProduct(Product product) {
        // do nothing - TODO
    }
}
