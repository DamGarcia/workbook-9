package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.sql.ResultSet;
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
    
    @RequestMapping(path="/categories", method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){
        return categoryDAO.addCategory(category);
    }
}
