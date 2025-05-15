package com.pichincha.sp.async.transaction.ingestor;

import com.pichincha.sp.async.transaction.ingestor.infrastructure.config.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(KafkaConfig.class)
public class OptimusMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimusMainApplication.class, args);
    }
}