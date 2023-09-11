package com.bank.repository;

import com.bank.enums.TransactionEnums;
import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true,
             value = " SELECT COUNT(*) FROM transaction t " +
                     " WHERE t.date > current_date AND t.source_account = :sourceAccountId " +
                     " AND t.type = :transactionType ")
    int countTransactionByDateTransaction(@Param("sourceAccountId") long sourceAccountId,
                                                     @Param("transactionType") String transactionType);

    @Query(nativeQuery = true,
                 value = " SELECT SUM(value) " +
                         " FROM transaction t " +
                         " WHERE source_account = :sourceAccountId " +
                         " AND t.type = :transactionType " +
                         " AND t.date = :today ")
    BigDecimal totalValueTransactionByType(@Param("sourceAccountId") long sourceAccountId,
                                                  @Param("transactionType") String transactionType,
                                                  @Param("today") SimpleDateFormat today);
}