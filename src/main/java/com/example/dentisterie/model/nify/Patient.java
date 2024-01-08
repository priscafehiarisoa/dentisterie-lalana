package com.example.dentisterie.model.nify;

import com.example.dentisterie.repository.nify.PatientRepository;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nomPatient;

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient(Integer id, String nomPatient) {
        setId(id);
        setNomPatient(nomPatient);
    }

    public Patient(String nomPatient) {
        setNomPatient(nomPatient);
    }

    public Patient() {
    }

    public Patient getPatientFromId(PatientRepository patientRepository,Integer id) throws Exception {
        Optional<Patient> patientOptional= patientRepository.findById(id);
        Patient patient;
        if(patientOptional.isPresent()){
            patient=patientOptional.get();
        }
        else{
            throw new Exception("patient inexistant");
        }
        return patient;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nomPatient='" + nomPatient + '\'' +
                '}';
    }
}
