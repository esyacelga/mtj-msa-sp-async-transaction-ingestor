package com.pichincha.sp.async.transaction.ingestor.application.input.port;

import com.pichincha.sp.async.transaction.ingestor.application.service.impl.IngestorServiceImpl;
import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)

/*@SpringBootTest
*/
@SpringBootTest
@EnableJpaRepositories(basePackages = "com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository")
@EntityScan(basePackages = "com.pichincha.sp.async.transaction.ingestor.domain.entities")
@TestPropertySource(locations = "classpath:application.yml")
public class IngestorServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private IngestorServiceImpl ingestorService;

    @Test
    public void findAllTransactions() {
        Transaction transaction = new Transaction();
        when(transactionRepository.findAll()).thenReturn(List.of(transaction));
        List<Transaction> result = ingestorService.findAllTransactions();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
    }

}


