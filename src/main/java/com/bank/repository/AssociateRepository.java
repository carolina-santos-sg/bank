package com.bank.repository;

import com.bank.model.Associates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associates, Long>{
    @Query (nativeQuery = true,
                  value = "SELECT COUNT(*) > 0 " +
                          "FROM associate a " +
                          "WHERE a.document_number = :documentNumber")
    public boolean countByDocumentoNumber(@Param("documentNumber") long documentNumber);

     @Query (nativeQuery = true,
                  value = "SELECT COUNT(*) > 0 " +
                          "FROM associate a " +
                          "WHERE a.associate_id = :id")
    public boolean countByAssociateId(@Param("id") long id);
}