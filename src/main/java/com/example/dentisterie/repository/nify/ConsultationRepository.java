package com.example.dentisterie.repository.nify;

import com.example.dentisterie.model.nify.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
}