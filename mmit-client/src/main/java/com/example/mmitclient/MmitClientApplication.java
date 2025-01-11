package com.example.mmitclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication //(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MmitClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmitClientApplication.class, args);
    }

}
