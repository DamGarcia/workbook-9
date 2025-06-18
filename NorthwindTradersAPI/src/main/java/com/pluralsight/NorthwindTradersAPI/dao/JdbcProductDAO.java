package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
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
public class JdbcProductDAO implements ProductDAO {

    private final DatabaseConfig databaseConfig;
    
    @Autowired
    public JdbcProductDAO(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        try (BasicDataSource bds = new BasicDataSource()) {
            bds.setUsername(databaseConfig.getUsername());
            bds.setPassword(databaseConfig.getPassword());
            bds.setUrl(databaseConfig.getUrl());


            String query = """
                 select productId, productName, CategoryId, UnitPrice
                 from products p
                """;

            try(Connection c = bds.getConnection();
                PreparedStatement s = c.prepareStatement(query);
                ResultSet result = s.executeQuery())
            {

                while(result.next()){
                    int productId = result.getInt(1);
                    String productName = result.getString(2);
                    int categoryId = result.getInt(3);
                    double unitPrice = result.getDouble(4);
                    Product p = new Product(productId, productName, categoryId, unitPrice);

                    allProducts.add(p);

                }

            } catch (SQLException e){
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return allProducts;
    }

    @Override
    public Product getProductByID(int userDefinedProductID) {
        
        Product p = null;
            
        try(BasicDataSource bds = new BasicDataSource()){

            bds.setUsername(databaseConfig.getUsername());
            bds.setPassword(databaseConfig.getPassword());
            bds.setUrl(databaseConfig.getUrl());

            List<Product> productsByID = new ArrayList<>();

            String query = """
                select
                               productId,
                               productName,
                               CategoryId,
                               UnitPrice
                               from
                               products
                               WHERE ProductID = ?""";


            try(Connection c = bds.getConnection();
                PreparedStatement s = c.prepareStatement(query))
            {

                s.setInt(1, userDefinedProductID);
                ResultSet result = s.executeQuery();


                while(result.next()){
                    String productName = result.getString(2);
                    int categoryId = result.getInt(1);
                    double unitPrice = result.getDouble(3);

                    p = new Product(userDefinedProductID, productName, categoryId, unitPrice);
                    
                }
                
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return p;
    }

    @Override
    public Product getProductByName(String productName) {
        Product p = null;
        
        try(BasicDataSource bds = new BasicDataSource()){
            bds.setUsername(databaseConfig.getUsername());
            bds.setPassword(databaseConfig.getPassword());
            bds.setUrl(databaseConfig.getUrl());
            
            
            String query = """
                    select productId, productName, CategoryId, UnitPrice
                    from products p
                    where p.ProductName  = ?;
                    """;
            
            try (Connection c = bds.getConnection();
            PreparedStatement s = c.prepareStatement(query))
            {
                s.setString(1, productName);
                
                ResultSet result = s.executeQuery();
                while(result.next()){
                    int productId = result.getInt(1);
                    int categoryId = result.getInt(3);
                    double unitPrice = result.getDouble(4);
                    
                    p = new Product(productId, productName, categoryId, unitPrice);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    @Override
    public Product addProduct(Product product) {
        // we will do this later
        return new Product();
    }
    
}
