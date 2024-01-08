package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoriqueIntervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    int etatInitiale;
    int etatFinale;
    @ManyToOne
    @JoinColumn(name = "etat_dent_id")
    EtatDent etatDent;

    LocalDateTime dateIntervention;

    double prixIntervention;

    double resteBudget;

    public double getResteBudget() {
        return resteBudget;
    }

    public void setResteBudget(double resteBudget) {
        this.resteBudget = resteBudget;
    }

    public HistoriqueIntervention() {

    }

    public double getPrixIntervention() {
        return prixIntervention;
    }

    public void setPrixIntervention(double prixIntervention) {
        this.prixIntervention = prixIntervention;
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

    public LocalDateTime getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(LocalDateTime dateIntervention) {
        this.dateIntervention = dateIntervention;
    }



    public EtatDent getEtatDent() {
        return etatDent;
    }

    public void setEtatDent(EtatDent etatDent) {
        this.etatDent = etatDent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public HistoriqueIntervention(Integer id, int etatInitiale, int etatFinale, EtatDent etatDent, LocalDateTime dateIntervention) {
        setId(id);
        setEtatInitiale(etatInitiale);
        setEtatFinale(etatFinale);
        setEtatDent(etatDent);
        setDateIntervention(dateIntervention);
    }
    public HistoriqueIntervention( int etatInitiale, int etatFinale, EtatDent etatDent, LocalDateTime dateIntervention) {
        setEtatInitiale(etatInitiale);
        setEtatFinale(etatFinale);
        setEtatDent(etatDent);
        setDateIntervention(dateIntervention);
    }

    @Override
    public String toString() {
        return "HistoriqueIntervention{" +
                "id=" + id +
                ", etatInitiale=" + etatInitiale +
                ", etatFinale=" + etatFinale +
                ", etatDent=" + etatDent +
                ", dateIntervention=" + dateIntervention +
                ", prixIntervention=" + prixIntervention +
                ", reste budget=" + resteBudget +
                '}';
    }
}
