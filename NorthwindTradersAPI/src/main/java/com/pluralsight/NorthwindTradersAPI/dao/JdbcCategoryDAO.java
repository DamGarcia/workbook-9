package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDAO implements CategoryDAO {

    private final DatabaseConfig databaseConfig;
    private final BasicDataSource bds;

    @Autowired
    public JdbcCategoryDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        this.bds = new BasicDataSource();

        bds.setUsername(databaseConfig.getUsername());
        bds.setPassword(databaseConfig.getPassword());
        bds.setUrl(databaseConfig.getUrl());
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();

        String query = """
                select CategoryID, CategoryName
                from categories c;
                """;

        try (Connection c = bds.getConnection();
             PreparedStatement s = c.prepareStatement(query);
             ResultSet result = s.executeQuery()) {
            while (result.next()) {
                int categoryID = result.getInt(1);
                String categoryName = result.getString(2);

                Category category = new Category(categoryID, categoryName);
                allCategories.add(category);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allCategories;

    }

    @Override
    public Category getCategoryByID(int categoryID) {
        String query = """
                select CategoryID, CategoryName
                from categories c;
                """;

        Category category = null;
        try (Connection c = bds.getConnection();
             PreparedStatement s = c.prepareStatement(query);
             ResultSet result = s.executeQuery()) {
            while (result.next()) {
                String categoryName = result.getString(2);

                category = new Category(categoryID, categoryName);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category category = null;
                
                String query = """
                        select CategoryID, CategoryName
                        from categories c
                        where c.CategoryName = ?;
                        """;
                
                try (Connection c = bds.getConnection();
                PreparedStatement s = c.prepareStatement(query))
                {
                    s.setString(1, categoryName);
                    
                    ResultSet result = s.executeQuery();
                    
                    while(result.next()){
                        int categoryId = result.getInt(1);
                        
                        category = new Category(categoryId, categoryName);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        return category;
    }

    @Override
    public Category addCategory(Category category) {

        String query = """
                insert into categories
                (CategoryName)
                values
                (?)
                """;
        
        try (Connection c = bds.getConnection();
        PreparedStatement s = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            
            s.setString(1, category.getCategoryName());
            
            int rowsAffected = s.executeUpdate();
            try (ResultSet keys = s.getGeneratedKeys()){
                while(keys.next()){
                    Category result = new Category();
                    result.setCategoryID(keys.getInt(1));
                    result.setCategoryName(category.getCategoryName());
                    return result;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
