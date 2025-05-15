package com.pichincha.sp.async.transaction.ingestor.application.output.port;

import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;

public interface TransactionKafkaPublisherPort {
    void publish(Transaction transaction);
}