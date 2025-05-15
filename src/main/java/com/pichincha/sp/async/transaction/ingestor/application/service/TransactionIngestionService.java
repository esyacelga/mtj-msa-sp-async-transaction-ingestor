package com.pichincha.sp.async.transaction.ingestor.application.service;

import com.pichincha.sp.async.transaction.ingestor.application.input.port.TransactionJobPort;
import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionKafkaPublisherPort;
import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionProcessor;
import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionIngestionService implements TransactionJobPort {
    private final TransactionRepositoryPort transactionRepository;
    private final TransactionKafkaPublisherPort kafkaPublisher;
    private final TransactionProcessor transactionProcessor;

    @Value("${app.batch-size:100}")
    private int batchSize;

    @Override
    public CompletableFuture<Integer> ingestTransactions() {
        try {
            transactionRepository.markNullStatusAsPending();
            List<UUID> pendingIds = transactionRepository.findPendingTransactionIdsWithLock(batchSize);
            if (pendingIds.isEmpty()) {
                return CompletableFuture.completedFuture(0);
            }
            transactionRepository.markTransactionsAsProcessing(pendingIds);
            List<UUID> successfulIds = new ArrayList<>();
            List<UUID> failedIds = new ArrayList<>();

            for (UUID id : pendingIds) {
                try {
                    transactionProcessor.processTransaction(id);
                    successfulIds.add(id);
                } catch (Exception e) {
                    log.error("Error processing transaction {}: {}", id, e.getMessage());
                    failedIds.add(id);
                }
            }
            if (!successfulIds.isEmpty()) {
                transactionRepository.markTransactionsAsProcessed(successfulIds);
            }

            if (!failedIds.isEmpty()) {
                transactionRepository.markTransactionsAsProcessing(failedIds);
            }

            return CompletableFuture.completedFuture(successfulIds.size());

        } catch (Exception e) {
            log.error("Error en el proceso de ingestión: {}", e.getMessage());
            throw new RuntimeException("Error en el proceso de ingestión", e);
        }


       /* List<Transaction> transactions = transactionRepository.findPendingTransactions();
        transactions.forEach(transaction -> {
            // Update status to processing
            transaction.setStatus("processing");
            transactionRepository.update(transaction);
            // Send to Kafka
            kafkaPublisher.publish(transaction);
            // Mark as processed
            transaction.setStatus("processed");
            transactionRepository.update(transaction);
        });*/
    }
}