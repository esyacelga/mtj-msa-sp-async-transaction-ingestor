package com.pichincha.sp.async.transaction.ingestor.infrastructure.adapter;

import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionKafkaPublisherPort;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTransactionPublisherAdapter implements TransactionKafkaPublisherPort {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaTransactionPublisherAdapter(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(Transaction transaction) {
        kafkaTemplate.send("tmp_tcb_reconciliation-transaction", transaction);
    }
}