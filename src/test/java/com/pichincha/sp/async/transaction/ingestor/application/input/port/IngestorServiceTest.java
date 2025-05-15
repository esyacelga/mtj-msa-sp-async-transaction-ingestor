package com.pichincha.sp.async.transaction.ingestor.application.input.port;

import com.pichincha.sp.async.transaction.ingestor.application.service.impl.IngestorServiceImpl;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
public class IngestorServiceTest {

    @Autowired
    private IngestorServiceImpl ingestorService;

    @Test
    public void findAllTransactions() {
        List<Transaction> result = ingestorService.findAllTransactions();
        assertNotNull(result);
        assertFalse(result.isEmpty()); // suponiendo que tienes al menos 1 registro
    }
}
