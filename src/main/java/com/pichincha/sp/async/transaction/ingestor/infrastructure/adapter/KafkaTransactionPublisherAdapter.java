package com.pichincha.sp.async.transaction.ingestor.infrastructure.adapter;

import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionKafkaPublisherPort;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class KafkaTransactionPublisherAdapter implements TransactionKafkaPublisherPort {


    @Override
    public void publish(Transaction transaction) {
        // kafkaTemplate.send("tmp_tcb_reconciliation-transaction", transaction);
    }
}