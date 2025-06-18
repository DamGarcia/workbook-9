package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this keyword tells spring boot that this is a controller aka an interface that exposes
// functionality to the user to have access to data
@RestController
public class ProductsController {
    
    private final ProductDAO productDAO;
    
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
    @RequestMapping(path="/products/byID/{productID}", method= RequestMethod.GET)
    public Product getProductsByID(@PathVariable int productID){
       return productDAO.getProductByID(productID);
    }

    @RequestMapping (path="/products/byName/{productName}", method= RequestMethod.GET)
    public Product getProductsByName(@PathVariable String productName){
        return productDAO.getProductByName(productName);
    }
    
    @RequestMapping(path="/products", method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productDAO.addProduct(product);
    }
}
