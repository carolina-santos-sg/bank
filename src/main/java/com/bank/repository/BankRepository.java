package com.bank.repository;

import com.bank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    @Query(nativeQuery = true,
                 value = "SELECT COUNT(*) > 0 " +
                         "FROM bank b " +
                         "WHERE b.bank_name = :bankName ")
    public boolean countBankByName(@Param("bankName") String bankName);

    @Query(nativeQuery = true,
             value = "SELECT COUNT(*) > 0 " +
                     "FROM bank b " +
                     "WHERE b.bank_number = :bankNumber ")
    public boolean countBankByNumber(@Param("bankNumber") long bankNumber);

}
