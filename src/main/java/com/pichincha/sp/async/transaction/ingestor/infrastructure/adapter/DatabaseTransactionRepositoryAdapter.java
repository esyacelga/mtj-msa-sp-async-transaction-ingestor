package com.pichincha.sp.async.transaction.ingestor.infrastructure.adapter;

import com.pichincha.sp.async.transaction.ingestor.application.output.port.TransactionRepositoryPort;
import com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseTransactionRepositoryAdapter implements TransactionRepositoryPort {
    private final TransactionRepository transactionRepository;

    @Override
    public int markNullStatusAsPending() {
        return transactionRepository.markNullStatusAsPending();
    }

    @Override
    public List<UUID> findPendingTransactionIdsWithLock(int limit) {
        return transactionRepository.findPendingTransactionIdsWithLock(limit);
    }

    @Override
    public int markTransactionsAsProcessing(List<UUID> ids) {
        return transactionRepository.markTransactionsAsProcessing(ids);
    }

    @Override
    public int markTransactionsAsProcessed(List<UUID> ids) {
        return transactionRepository.markTransactionsAsProcessed(ids);
    }
}