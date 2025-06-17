package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// this keyword tells spring boot that this is a controller aka an interface that exposes
// functionality to the user to have access to data
@RestController
public class ProductsController {
    
    private ProductDAO productDAO;
    
    @Autowired
    ProductsController(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    
    @RequestMapping(path="/products", method= RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    // within the path variable, this is one solution to solving the ambiguous methods error when running the API
    // aka lets the program know which method to run by making the path unique
    @RequestMapping(path="/products/byID/{id}", method= RequestMethod.GET)
    public Product getProductsByID(@PathVariable int id){
       return productDAO.getProductByID(id);
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
