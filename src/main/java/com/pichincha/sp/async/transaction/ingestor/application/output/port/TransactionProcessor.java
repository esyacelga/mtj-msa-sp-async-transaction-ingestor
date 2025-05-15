package com.pichincha.sp.async.transaction.ingestor.application.output.port;

import java.util.UUID;

public interface TransactionProcessor {
    void processTransaction(UUID transactionId);
}
