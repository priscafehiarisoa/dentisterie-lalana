package com.example.dentisterie.model.lalana;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HIstoriqueReparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    LocalDateTime dateHistorique;
    @ManyToOne
    @JoinColumn(name = "etat_routes_id")
    EtatRoutes etatRoutes;
    int etatInitiale;
    int etatFinale;
    @ManyToOne
    @JoinColumn(name = "construction_id")
    Construction construction;
    double coutDepenses;
    double resteBudget;

    public LocalDateTime getDateHistorique() {
        return dateHistorique;
    }

    public void setDateHistorique(LocalDateTime dateHistorique) {
        this.dateHistorique = dateHistorique;
    }

    public int getEtatInitiale() {
        return etatInitiale;
    }

    public void setEtatInitiale(int etatInitiale) {
        this.etatInitiale = etatInitiale;
    }

    public int getEtatFinale() {
        return etatFinale;
    }

    public void setEtatFinale(int etatFinale) {
        this.etatFinale = etatFinale;
    }

    public double getCoutDepenses() {
        return coutDepenses;
    }

    public void setCoutDepenses(double coutDepenses) {
        this.coutDepenses = coutDepenses;
    }

    public double getResteBudget() {
        return resteBudget;
    }

    public void setResteBudget(double resteBudget) {
        this.resteBudget = resteBudget;
    }

    public EtatRoutes getEtatRoutes() {
        return etatRoutes;
    }

    public void setEtatRoutes(EtatRoutes etatRoutes) {
        this.etatRoutes = etatRoutes;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void setConstruction(Construction construction) {
        this.construction = construction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HIstoriqueReparation(Integer id, LocalDateTime dateHistorique, EtatRoutes etatRoutes, int etatInitiale, int etatFinale, Construction construction, double coutDepenses, double resteBudget) {
        this.id = id;
        this.dateHistorique = dateHistorique;
        this.etatRoutes = etatRoutes;
        this.etatInitiale = etatInitiale;
        this.etatFinale = etatFinale;
        this.construction = construction;
        this.coutDepenses = coutDepenses;
        this.resteBudget = resteBudget;
    }

    public HIstoriqueReparation(LocalDateTime dateHistorique, EtatRoutes etatRoutes, int etatInitiale, int etatFinale, Construction construction, double coutDepenses, double resteBudget) {
        this.dateHistorique = dateHistorique;
        this.etatRoutes = etatRoutes;
        this.etatInitiale = etatInitiale;
        this.etatFinale = etatFinale;
        this.construction = construction;
        this.coutDepenses = coutDepenses;
        this.resteBudget = resteBudget;
    }

    public HIstoriqueReparation() {
    }

    @Override
    public String toString() {
        return "HIstoriqueReparation{" +
                "id=" + id +
                ", dateHistorique=" + dateHistorique +
                ", etatRoutes=" + etatRoutes +
                ", etatInitiale=" + etatInitiale +
                ", etatFinale=" + etatFinale +
                ", construction=" + construction +
                ", coutDepenses=" + coutDepenses +
                ", resteBudget=" + resteBudget +
                '}';
    }
}
