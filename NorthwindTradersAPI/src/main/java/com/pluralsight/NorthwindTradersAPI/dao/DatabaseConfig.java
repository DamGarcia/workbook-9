package com.pluralsight.NorthwindTradersAPI.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// this keyword tells spring boot that this is a necessary class for the function of the server
@Component
public class DatabaseConfig {
    private String username;
    private String password;
    private String url;

    // the values are found within the application.properties within the resources package
    // we set these values so we don't have to hardcode them
    public DatabaseConfig(@Value("${datasource.username}") String username,
                          @Value("${datasource.password}") String password,
                          @Value("${datasource.url}") String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
