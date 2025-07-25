package com.pluralsight.webdemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
public class HomeControllers {
    
        @RequestMapping(path="/",method= RequestMethod.GET)
        public String index(@RequestParam(defaultValue = "World") String name) {
            return "<h1>Hello " + name + "</h1> <p>We hope you are having a great day!</p>";
        }
}
