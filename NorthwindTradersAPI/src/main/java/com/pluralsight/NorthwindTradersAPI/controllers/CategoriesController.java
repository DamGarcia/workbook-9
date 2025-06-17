package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class CategoriesController {
    
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoriesController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(path="/categories", method= RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }
    
    @RequestMapping(path="/categories/byID/{categoryID}", method= RequestMethod.GET)
    public Category getCategoryByID(@PathVariable int categoryID){
        return categoryDAO.getCategoryByID(categoryID);
    }
    
    @RequestMapping(path="/categories/byName/{categoryName}", method= RequestMethod.GET)
    public Category getCategoryByName(@PathVariable String categoryName){
        return categoryDAO.getCategoryByName(categoryName);
    }
}
