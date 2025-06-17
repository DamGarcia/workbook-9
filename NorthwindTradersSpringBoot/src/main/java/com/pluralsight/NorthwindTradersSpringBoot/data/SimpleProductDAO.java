package com.pluralsight.NorthwindTradersSpringBoot.data;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// @Component
public class SimpleProductDAO implements ProductDAO{
    // can rewrite the HashMap into a List later
    private HashMap<Integer, Product> products = new HashMap<Integer, Product>();
    
    public SimpleProductDAO(){
        this.products.put(1, new Product(1, "Milk", "Food", 10.99));
        this.products.put(2, new Product(1, "Apple", "Food", 3.99));
        this.products.put(3, new Product(1, "Pants", "Clothing", 25.00));
        this.products.put(4, new Product(1, "Shirt", "Clothing", 19.99));
    }
    
    @Override
    public void addProduct(Product product){
        this.products.put(product.getProductId(), product);
    };
    
    @Override
    public List<Product> getAll(){
        return new ArrayList<Product>(this.products.values());
    };
    
}
