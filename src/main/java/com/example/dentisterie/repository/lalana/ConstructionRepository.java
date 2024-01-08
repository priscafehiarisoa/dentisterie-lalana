package com.example.dentisterie.repository.lalana;

import com.example.dentisterie.model.lalana.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ConstructionRepository extends JpaRepository<Construction, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update Construction p set p.etat=1 where p.id = :id")
    void updateConstruction(@Param("id") Integer idConstruction);

}