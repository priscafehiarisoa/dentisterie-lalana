package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

@Entity
public class Priorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nomPriorite;

    public String getNomPriorite() {
        return nomPriorite;
    }

    public void setNomPriorite(String nomPriorite) {
        this.nomPriorite = nomPriorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Priorites(Integer id, String nomPriorite) {
        setId(id);
        setNomPriorite(nomPriorite);
    }
    public Priorites(String nomPriorite) {
        setNomPriorite(nomPriorite);
    }

    public Priorites() {
    }
}
