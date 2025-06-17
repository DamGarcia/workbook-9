package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDAO implements CategoryDAO {

    private final DatabaseConfig databaseConfig;

    @Autowired
    public JdbcCategoryDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public List<Category> getAllCategories() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(databaseConfig.getUsername());
        bds.setPassword(databaseConfig.getPassword());
        bds.setUrl(databaseConfig.getUrl());

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
        
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(databaseConfig.getUsername());
        bds.setPassword(databaseConfig.getPassword());
        bds.setUrl(databaseConfig.getUrl());

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
        
        try(BasicDataSource bds = new BasicDataSource()){
            bds.setUsername(databaseConfig.getUsername());
            bds.setPassword(databaseConfig.getPassword());
            bds.setUrl(databaseConfig.getUrl());
            {
                
                String query = """
                        select CategoryID, CategoryName
                        from categories c
                        where c.CategoryName = ?;
                        """;
                
                try (Connection c = bds.getConnection();
                PreparedStatement s = c.prepareStatement(query);)
                {
                    s.setString(1, categoryName);
                    
                    ResultSet result = s.executeQuery();
                    
                    while(result.next()){
                        int categoryId = result.getInt(1);
                        
                        category = new Category(categoryId, categoryName);
                    }
                }
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return category;
    }

    @Override
    public void addCategory(Category category) {

    }
}
