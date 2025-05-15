package com.pichincha.sp.async.transaction.ingestor.infrastructure.adapter;

import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionProcessor;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultTransactionProcessor implements TransactionProcessor {
    private final TransactionRepository transactionRepository;
    /// private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;


    @Override
    public void processTransaction(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalStateException("Transaction not found: " + transactionId));

        /// kafkaTemplate.send(topic, transaction);
        log.info("Sent transaction {} to Kafka topic {}", transactionId, topic);
    }

    /*@Override
    public void processTransaction(UUID transactionId) {

    }*/
}