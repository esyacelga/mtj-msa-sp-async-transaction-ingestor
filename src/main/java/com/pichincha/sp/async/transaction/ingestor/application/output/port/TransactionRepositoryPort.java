package com.pichincha.sp.async.transaction.ingestor.application.output.port;

import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepositoryPort {
    List<Transaction> findPendingTransactions();
    void update(Transaction transaction);

    int markNullStatusAsPending();


    List<UUID> findPendingTransactionIdsWithLock(@Param("limit") int limit);

    int markTransactionsAsProcessing(@Param("ids") List<UUID> ids);

    int markTransactionsAsProcessed(@Param("ids") List<UUID> ids);

}