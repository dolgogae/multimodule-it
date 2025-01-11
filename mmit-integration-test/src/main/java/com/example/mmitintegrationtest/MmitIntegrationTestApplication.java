package com.example.mmitintegrationtest;

import com.example.mmitintegrationtest.generator.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
        scanBasePackages = {
                "com.example.mmitclient",
                "com.example.mmitservice"
        }
//        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class MmitIntegrationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmitIntegrationTestApplication.class, args);
    }

}
