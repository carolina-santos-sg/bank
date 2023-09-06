package com.bank.repository;

import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true,
                 value = " SELECT ba.balance FROM bank_account ba WHERE ba.id = :id ")
    double selectBalanceByAccountId(@Param("id") long id);
}