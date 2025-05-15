package com.pichincha.sp.async.transaction.ingestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository")
@EntityScan(basePackages = "com.pichincha.sp.async.transaction.ingestor.domain.entities")
public class OptimusMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimusMainApplication.class, args);
    }
}