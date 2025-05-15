package com.pichincha.sp.async.transaction.ingestor.application.input.port;

import java.util.concurrent.CompletableFuture;

public interface TransactionJobPort {
    CompletableFuture<Integer> ingestTransactions();
}