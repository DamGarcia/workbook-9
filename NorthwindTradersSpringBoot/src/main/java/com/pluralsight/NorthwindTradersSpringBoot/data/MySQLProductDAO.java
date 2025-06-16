package com.pluralsight.NorthwindTradersSpringBoot.data;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
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
public class MySQLProductDAO implements ProductDAO {

    private final DatabaseConfig databaseConfig;
    
    @Autowired
    public MySQLProductDAO(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }
    
    @Override
    public void addProduct(Product product) {
        //TODO
    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> allProducts = new ArrayList<>();
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(databaseConfig.getUsername());
        bds.setPassword(databaseConfig.getPassword());
        bds.setUrl(databaseConfig.getUrl());
        
        String query = """
                select
                productId,
                productName,
                CategoryName,
                UnitPrice
                from northwind.products
                join categories c on products.CategoryID = c.CategoryID;
                """;
        
        try(Connection c = bds.getConnection();
            PreparedStatement s = c.prepareStatement(query);
            ResultSet result = s.executeQuery())
        {

            while(result.next()){
                int productID = result.getInt("productId");
                String productName = result.getString("ProductName");
                String categoryName = result.getString("CategoryName");
                double unitPrice = result.getDouble("UnitPrice");
                
                Product p = new Product(productID, productName, categoryName, unitPrice);
                allProducts.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return allProducts;
    }
}
