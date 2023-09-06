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
    boolean countByAgencyNumber(@Param("id") long id);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM bank_agency ba " +
                    "WHERE ba.bank_number = :bankNumber")
    boolean countByBankNumber(@Param("bankNumber") long bankNumber);

    @Query(nativeQuery = true,
                 value = " SELECT COUNT(*) > 0 " +
                         " FROM bank_agency ba " +
                         " JOIN bank b ON b.bank_number = ba.bank_number " +
                         " WHERE ba.number_agency = :agencyNumber " +
                         " AND ba.bank_number = :bankNumber ")
    boolean countAgencyAndBankByNumber(@Param("agencyNumber") Long agencyNumber, @Param("bankNumber") Long bankNumber);

    @Query(nativeQuery = true,
            value = " SELECT ba.bank_number " +
                    " FROM bank_agency ba JOIN bank_account ba2 " +
                    " ON ba2.agency_id = ba.id " +
                    " WHERE ba2.agency_id = :agencyId")
    long selectBankNumberByAgencyId(@Param("agencyId") long agencyId);
}
