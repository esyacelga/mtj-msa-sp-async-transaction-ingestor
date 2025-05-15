package com.pichincha.sp.async.transaction.ingestor.infrastructure.scheduler;

import com.pichincha.sp.async.transaction.ingestor.application.input.port.TransactionJobPort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TransactionIngestionScheduler {
    private final TransactionJobPort transactionJobPort;

    public TransactionIngestionScheduler(TransactionJobPort transactionJobPort) {
        this.transactionJobPort = transactionJobPort;
    }

    @Scheduled(cron = "0 0 3 * * ?") // Ejecutar a las 3 AM
    public void scheduleJob() {
        transactionJobPort.ingestTransactions();
    }
}