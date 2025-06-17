package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {


    @RequestMapping(path="/categories", method= RequestMethod.GET)
    public List<Category> getAllCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Food"));
        categories.add(new Category(2, "Beverages"));
        categories.add(new Category(3, "Dessert"));

        return categories;
    }


    @RequestMapping(path="/categories/{id}", method= RequestMethod.GET)
    public List<Category> getCategoriesID(@PathVariable int id){
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Food"));
        categories.add(new Category(2, "Beverages"));
        categories.add(new Category(3, "Dessert"));
        

        for(Category c : categories){
            if(c.getCategoryID() == id){
                ArrayList<Category> resultCategories = new ArrayList<>();
                resultCategories.add(c);
                return resultCategories;
            }
        }

        return new ArrayList<Category>();
    }
}
