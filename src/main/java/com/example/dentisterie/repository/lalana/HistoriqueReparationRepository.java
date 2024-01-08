package com.example.dentisterie.repository.lalana;

import com.example.dentisterie.model.lalana.Construction;
import com.example.dentisterie.model.lalana.HIstoriqueReparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueReparationRepository extends JpaRepository<HIstoriqueReparation, Integer> {
    List<HIstoriqueReparation> getHIstoriqueReparationByConstruction(Construction construction);
}