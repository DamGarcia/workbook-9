package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    
    @RequestMapping(path="/products", method= RequestMethod.GET)
    public List<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 1, 5.99));
        products.add(new Product(2, "Apples", 1, 5.99));
        products.add(new Product(3, "Water", 1, 5.99));
        products.add(new Product(4, "Shoes", 2, 5.99));
        products.add(new Product(5, "Shirt", 2, 5.99));
        
        return products;
    }

    // within the path variable, this is one solution to solving the ambiguous methods error when running the API
    // aka lets the program know which method to run by making the path unique
    @RequestMapping(path="/products/byID/{id}", method= RequestMethod.GET)
    public List<Product> getProductsByID(@PathVariable int id){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 1, 5.99));
        products.add(new Product(2, "Apples", 1, 5.99));
        products.add(new Product(3, "Water", 1, 5.99));
        products.add(new Product(4, "Shoes", 2, 5.99));
        products.add(new Product(5, "Shirt", 2, 5.99));

        for(Product p : products){
            if(p.getProductID() == id){
                ArrayList<Product> resultProducts = new ArrayList<>();
                resultProducts.add(p);
                return resultProducts;
            }
        }
        
        return new ArrayList<Product>();
    }
    

    @RequestMapping (path="/products/byName/{productName}", method= RequestMethod.GET)
    public List<Product> getProductsByName(@PathVariable String productName){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 1, 5.99));
        products.add(new Product(2, "Apples", 1, 5.99));
        products.add(new Product(3, "Water", 1, 5.99));
        products.add(new Product(4, "Shoes", 2, 5.99));
        products.add(new Product(5, "Shirt", 2, 5.99));

//        for(Product p : products){
//            if(p.getProductName().equals(name)){
//                ArrayList<Product> resultProducts = new ArrayList<>();
//                resultProducts.add(p);
//                return resultProducts;
//            }
//        }
//
//        return new ArrayList<Product>();
        
        return products.stream()
                .filter(p -> p.getProductName().equalsIgnoreCase(productName))
                .toList();
    }
    
}
