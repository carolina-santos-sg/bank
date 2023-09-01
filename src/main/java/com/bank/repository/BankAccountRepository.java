package com.bank.repository;

import com.bank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query(nativeQuery = true,
                 value = "SELECT COUNT(*) > 0 " +
                         "FROM  bank_account ba " +
                         "WHERE ba.id = :id ")
    public boolean countByNumberAccount(@Param("id") long id);
}