package com.pluralsight;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        // what is the purpose of having this ApplicationContext
        // gives us access to configure the application
        ApplicationContext context;
        context = SpringApplication.run(DemoApplication.class, args);

//        for (String bean : context.getBeanDefinitionNames()) {
//            System.out.println(bean);

        CommandLineRunner clr = context.getBean(CLI.class);
        clr.run();
        }
    }

