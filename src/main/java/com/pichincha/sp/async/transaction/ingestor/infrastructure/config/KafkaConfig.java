package com.pichincha.sp.async.transaction.ingestor.infrastructure.config;

import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String, Transaction> kafkaTemplate(ProducerFactory<String, Transaction> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}