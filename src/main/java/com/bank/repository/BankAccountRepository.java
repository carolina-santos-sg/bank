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

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query(nativeQuery = true,
                 value = " SELECT COUNT(*) > 0 " +
                         " FROM  bank_account ba2 " +
                         " WHERE ba2.number_account = :numberAccount AND ba2.agency_id = :agencyId")
    boolean countByNumberAccountAndNumberAgency(@Param("numberAccount") Long numberAccount, @Param("agencyId") Long agencyId);

}