package com.pichincha.sp.async.transaction.ingestor.infrastructure;

import com.pichincha.sp.async.transaction.ingestor.infrastructure.adapter.DatabaseTransactionRepositoryAdapter;
import com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DatabaseTransactionRepositoryAdapterTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DatabaseTransactionRepositoryAdapter databaseTransactionRepositoryAdapter;


    @Test
    void testMarkNullStatusAsPending() {
        when(transactionRepository.markNullStatusAsPending()).thenReturn(1);

        int result = databaseTransactionRepositoryAdapter.markNullStatusAsPending();

        assertEquals(1, result);
        verify(transactionRepository, times(1)).markNullStatusAsPending();
    }

    @Test
    @Description("Test the findPendingTransactionIdsWithLock method")
    void testFindPendingTransactionIdsWithLock() {
        UUID transactionId = UUID.randomUUID();
        when(transactionRepository.findPendingTransactionIdsWithLock(5)).thenReturn(Collections.singletonList(transactionId));

        List<UUID> result = databaseTransactionRepositoryAdapter.findPendingTransactionIdsWithLock(5);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transactionId, result.get(0));
        verify(transactionRepository, times(1)).findPendingTransactionIdsWithLock(5);
    }

    @Test
    @Description("Test the markTransactionsAsProcessing method")
    void testMarkTransactionsAsProcessing() {
        UUID transactionId = UUID.randomUUID();
        when(transactionRepository.markTransactionsAsProcessing(any())).thenReturn(1);

        int result = databaseTransactionRepositoryAdapter.markTransactionsAsProcessing(Collections.singletonList(transactionId));

        assertEquals(1, result);
        verify(transactionRepository, times(1)).markTransactionsAsProcessing(any());
    }

    @Test
    @Description("Test the markTransactionsAsProcessed method")
    void testMarkTransactionsAsProcessed() {
        UUID transactionId = UUID.randomUUID();
        when(transactionRepository.markTransactionsAsProcessed(any())).thenReturn(1);

        int result = databaseTransactionRepositoryAdapter.markTransactionsAsProcessed(Collections.singletonList(transactionId));

        assertEquals(1, result);
        verify(transactionRepository, times(1)).markTransactionsAsProcessed(any());
    }
}
