package com.example.mmitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication //(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MmitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmitServiceApplication.class, args);
    }

}
