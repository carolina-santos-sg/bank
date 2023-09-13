package com.bank.repository;

import com.bank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    @Query(nativeQuery = true,
                 value = " SELECT COUNT(*) > 0 " +
                         " FROM bank b " +
                         " WHERE b.bank_name = :bankName ")
    boolean countBankByName(@Param("bankName") String bankName);

    @Query(nativeQuery = true,
                 value = " SELECT COUNT(*) > 0 " +
                         " FROM bank b " +
                         " WHERE b.bank_number = :bankNumber ")
    boolean countBankByNumber(@Param("bankNumber") long bankNumber);

    @Query(nativeQuery = true,
                 value = " SELECT b.full_balance_transaction " +
                         " FROM bank b " +
                         " JOIN bank_agency ba ON ba.bank_number = b.bank_number " +
                         " JOIN bank_account ba2 ON ba2.agency_id = ba.id " +
                         " WHERE ba2.id = :accountId " +
                         " LIMIT 1 ")
    BigDecimal fullBalanceByBank (@Param("accountId") long accountId);

    @Query(nativeQuery = true,
                 value = " SELECT b.bank_number " +
                         " FROM bank b " +
                         " WHERE b.bank_name = :bankName ")
    Long findByBankName(@Param("bankName") String bankName);
}
