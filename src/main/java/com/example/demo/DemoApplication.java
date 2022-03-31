package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.example.demo", "com.example.demo.entities", "com.example.demo.repositories"})
@ComponentScan({"com.example.demo", "com.example.demo.entities", "com.example.demo.repositories"})
@EntityScan(basePackages = "com.example.demo.entities")
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
@EnableTransactionManagement

public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
