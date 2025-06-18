package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
    Category getCategoryByID(int categoryID);
    Category getCategoryByName(String categoryName);
    Category addCategory(Category category);
}
