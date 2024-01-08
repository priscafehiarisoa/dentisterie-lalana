package com.example.dentisterie.repository.lalana;

import com.example.dentisterie.model.lalana.EtatRoutes;
import com.example.dentisterie.model.lalana.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface EtatRoutesRepository extends JpaRepository<EtatRoutes, Integer> {

    @Query("select s from EtatRoutes s where s.etatNiveau<10 " +
            "and s.dateDeclarationEtat >=:dateConstruction or s.dateDeclarationEtat <:dateConstruction " +
            "and s.routes=:routes")
    List<EtatRoutes> getEtatRoutesAReparer(@Param("dateConstruction")LocalDateTime dateConstruction, @Param("routes")Routes routes);

    @Modifying
    @Transactional
    @Query(value = "update EtatRoutes p set p.etatNiveau=:etat where p.id = :id")
    void updateEtatRoute(@Param("id") Integer id,@Param("etat") int etatfinal);
}