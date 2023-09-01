package com.bank.repository;

import com.bank.model.BankAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAgencyRepository extends JpaRepository<BankAgency, Long> {
    @Query(nativeQuery = true,
                 value = "SELECT COUNT(*) > 0 "  +
                         "FROM  bank_agency ba " +
                         "WHERE ba.id = :id")
    public boolean countByAgencyNumber(@Param("id") long id);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_agency ba " +
                    "WHERE ba.bank_number = :bankNumber")
    public boolean countByBankNumber(@Param("bankNumber") long bankNumber);
}