package com.bank.repository;

import com.bank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query(nativeQuery = true,
                 value = " SELECT COUNT(*) > 0 " +
                         " FROM  bank_account ba2 " +
                         " JOIN bank_agency ba ON ba.number_agency = ba2.number_agency" +
                         " WHERE ba2.number_account = :numberAccount " +
                         " AND ba2.number_agency = :numberAgency")
    boolean countByNumberAccountAndNumberAgency(@Param("numberAccount") long numberAccount, @Param("numberAgency") long numberAgency);

}