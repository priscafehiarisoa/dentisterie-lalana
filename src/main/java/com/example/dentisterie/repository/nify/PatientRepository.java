package com.example.dentisterie.repository.nify;

import com.example.dentisterie.model.nify.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}