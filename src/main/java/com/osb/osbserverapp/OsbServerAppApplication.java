package com.osb.osbserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.osb")
@SpringBootApplication
public class OsbServerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsbServerAppApplication.class, args);
    }

}
