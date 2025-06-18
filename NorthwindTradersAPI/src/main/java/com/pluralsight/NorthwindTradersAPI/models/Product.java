package com.pluralsight.NorthwindTradersAPI.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int productID;
    private String productName;
    private int categoryID;
    private double UnitPrice;

    public Product(int productID, String productName, int categoryID, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.UnitPrice = unitPrice;
    }

    public Product() { 
        // do nothing
    }
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", categoryID=" + categoryID +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}
