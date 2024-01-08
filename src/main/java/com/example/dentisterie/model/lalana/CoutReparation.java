package com.example.dentisterie.model.lalana;

import jakarta.persistence.*;

@Entity
public class CoutReparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    int debutIntervalle;
    int finIntervalle;
    double valeur;

    public int getDebutIntervalle() {
        return debutIntervalle;
    }

    public void setDebutIntervalle(int debutIntervalle) {
        this.debutIntervalle = debutIntervalle;
    }

    public int getFinIntervalle() {
        return finIntervalle;
    }

    public void setFinIntervalle(int finIntervalle) {
        this.finIntervalle = finIntervalle;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CoutReparation(Integer id, int debutIntervalle, int finIntervalle, double valeur) {
       setId(id);
        setDebutIntervalle(debutIntervalle);
        setFinIntervalle(finIntervalle);
        setValeur(valeur);
    }
    public CoutReparation( int debutIntervalle, int finIntervalle, double valeur) {
        setDebutIntervalle(debutIntervalle);
        setFinIntervalle(finIntervalle);
        setValeur(valeur);
    }

    public CoutReparation() {
    }

    @Override
    public String toString() {
        return "CoutReparation{" +
                "id=" + id +
                ", debutIntervalle=" + debutIntervalle +
                ", finIntervalle=" + finIntervalle +
                ", valeur=" + valeur +
                '}';
    }
}
