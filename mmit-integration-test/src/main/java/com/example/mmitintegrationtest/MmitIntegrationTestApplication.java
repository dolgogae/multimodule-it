package com.example.mmitintegrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.example.mmitclient",
                "com.example.mmitservice"
        }
)
public class MmitIntegrationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmitIntegrationTestApplication.class, args);
    }

}
