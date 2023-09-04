package com.bank.repository;

import com.bank.model.Associate;
import com.bank.service.AssociateService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long>{
    @Query (nativeQuery = true,
                  value = "SELECT COUNT(*) > 0 " +
                          "FROM associates a " +
                          "WHERE a.document_number = :documentNumber")
    boolean countByDocumentNumber(@Param("documentNumber") long documentNumber);


}