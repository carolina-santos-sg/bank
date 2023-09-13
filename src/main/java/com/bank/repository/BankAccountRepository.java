package com.bank.repository;

import com.bank.model.Bank;
import com.bank.model.BankAccount;
import com.bank.model.BankAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.math.BigDecimal;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query(nativeQuery = true,
                 value = " SELECT ba.agency_id " +
                         " FROM bank_account ba  " +
                         " WHERE ba.id = :bankAccountId ")
    Long getBankAgencyByAccountId(@Param("bankAccountId") Long bankAccountId);

    @Query (nativeQuery = true,
                  value = " SELECT COUNT(*) > 0 " +
                          " FROM bank_account ba " +
                          " JOIN bank_agency ba2 ON ba.agency_id = ba2.id " +
                          " JOIN bank b on ba2.bank_number = b.bank_number " +
                          " WHERE b.bank_number = :bankNumber and associate_id = :associateId")
    boolean countByAssociateAndBank(@Param("bankNumber") long bankNumber, @Param("associateId") long associateId);

    @Query(nativeQuery = true,
                 value = " SELECT ba.balance " +
                         " FROM bank_account ba " +
                         " WHERE ba.id = :accountId ")
    BigDecimal balanceByAccount(@Param("accountId") long accountId);
}