package com.pichincha.sp.async.transaction.ingestor.application.service.impl;

import com.pichincha.sp.async.transaction.ingestor.application.input.port.IngestorService;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IngestorServiceImpl implements IngestorService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> lst = transactionRepository.findAll();
        log.info("Transactions) found: {}", lst);
        return transactionRepository.findAll();
    }
}
