package com.example.dentisterie.model.lalana;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    LocalDateTime dateConstruction;
    double budget;
    @ManyToOne
    @JoinColumn(name = "routes_id")
    Routes routes;
    int priorite;
    int etat=0;

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LocalDateTime getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(LocalDateTime dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Construction(Integer id, LocalDateTime dateConstruction, double budget, Routes routes, int priorite) {
        this.id = id;
        this.dateConstruction = dateConstruction;
        this.budget = budget;
        this.routes = routes;
        this.priorite = priorite;
    }

    public Construction(LocalDateTime dateConstruction, double budget, Routes routes, int priorite) {
        this.dateConstruction = dateConstruction;
        this.budget = budget;
        this.routes = routes;
        this.priorite = priorite;
    }

    public Construction() {
    }
}
