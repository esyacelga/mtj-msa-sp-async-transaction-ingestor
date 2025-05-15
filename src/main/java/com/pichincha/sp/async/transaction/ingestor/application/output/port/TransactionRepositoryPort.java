package com.pichincha.sp.async.transaction.ingestor.application.output.port;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepositoryPort {

    int markNullStatusAsPending();

    List<UUID> findPendingTransactionIdsWithLock(@Param("limit") int limit);

    int markTransactionsAsProcessing(@Param("ids") List<UUID> ids);

    int markTransactionsAsProcessed(@Param("ids") List<UUID> ids);

}