package com.pichincha.sp.async.transaction.ingestor.application.input.port;

import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;

import java.util.List;

public interface IngestorService {

    List<Transaction> findAllTransactions();
}
