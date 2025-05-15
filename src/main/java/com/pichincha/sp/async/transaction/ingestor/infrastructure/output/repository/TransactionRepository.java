package com.pichincha.sp.async.transaction.ingestor.infrastructure.output.repository;

import com.pichincha.sp.async.transaction.ingestor.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Actualiza todos los registros cuyo status está en NULL a 'pending'
     */
    @Modifying
    @Query("UPDATE Transaction t SET t.status = 'pending' WHERE t.status IS NULL")
    int markNullStatusAsPending();

    /**
     * Selecciona IDs de transacciones pendientes, ordenadas por fecha, con bloqueo concurrente
     */
    @Query(value = """
            SELECT transaction_id
            FROM banco.transactions
            WHERE status = 'pending'
            ORDER BY created_at
            LIMIT :limit
            FOR UPDATE SKIP LOCKED
            """, nativeQuery = true)
    List<UUID> findPendingTransactionIdsWithLock(@Param("limit") int limit);

    /**
     * Marca un conjunto de transacciones como 'processing'
     */
    @Modifying
    @Query("UPDATE Transaction t SET t.status = 'processing' WHERE t.transactionId IN :ids")
    int markTransactionsAsProcessing(@Param("ids") List<UUID> ids);

    /**
     * Marca un conjunto de transacciones como 'processed'
     */
    @Modifying
    @Query("UPDATE Transaction t SET t.status = 'processed' WHERE t.transactionId IN :ids")
    int markTransactionsAsProcessed(@Param("ids") List<UUID> ids);
}