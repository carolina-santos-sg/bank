package com.bank.repository;


import com.bank.enums.TransactionEnums;
import com.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true,
            value = " SELECT (COALESCE(SUM(value),0)) " +
                    " FROM transaction t " +
                    " JOIN bank_account ba ON t.source_account = ba.id " +
                    " WHERE source_account = :sourceAccountId " +
                    " AND t.transaction_type = :transactionType " +
                    " AND t.date > CURRENT_DATE ")
    BigDecimal totalValueTransactionByTypeAndSourceAccount(@Param("sourceAccountId") Long sourceAccountId,
                                                           @Param("transactionType") String transactionType);

    @Query(nativeQuery = true,
             value = " SELECT COUNT(*) FROM transaction t " +
                     " WHERE t.date > CURRENT_DATE AND t.source_account = :sourceAccountId " +
                     " AND t.transaction_type = :transactionType ")
    int countTransactionByDateTransaction(@Param("sourceAccountId") Long sourceAccountId,
                                                     @Param("transactionType") String transactionType);

    @Query(nativeQuery = true, value = " SELECT * " +
                                       " FROM transaction t " +
                                       " WHERE t.date > CURRENT_DATE AND t.transaction_type = :transactionType" +
                                       " AND t.source_account = :accountId ")
    Transaction dataTransaction(@Param("transactionType") String transactionType, @Param("accountId") long accountId);

    @Query(nativeQuery = true,
            value = " SELECT (COALESCE(SUM(value),0)) " +
                    " FROM transaction t " +
                    " JOIN bank_account ba ON t.target_account = ba.id" +
                    " WHERE target_account = :targetAccountId " +
                    " AND t.transaction_type = :transactionType " +
                    " AND t.date > CURRENT_DATE ")
    BigDecimal totalValueTransactionByTypeAndTargetAccount(@Param("targetAccountId") Long targetAccountId,
                                                           @Param("transactionType") String transactionType);
}