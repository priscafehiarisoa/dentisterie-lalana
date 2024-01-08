package com.example.dentisterie.model.lalana;

import jakarta.persistence.*;

@Entity
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    String nonRoutes;
    int debitRoute;
    int finRoute;

    public String getNonRoutes() {
        return nonRoutes;
    }

    public void setNonRoutes(String nonRoutes) {
        this.nonRoutes = nonRoutes;
    }

    public int getDebitRoute() {
        return debitRoute;
    }

    public void setDebitRoute(int debitRoute) {
        this.debitRoute = debitRoute;
    }

    public int getFinRoute() {
        return finRoute;
    }

    public void setFinRoute(int finRoute) {
        this.finRoute = finRoute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Routes(Integer id, String nonRoutes, int debutRoute, int finRoute) {
        setId(id);
        setNonRoutes(nonRoutes);
        setDebitRoute(debutRoute);
        setFinRoute(finRoute);
    }

    public Routes(String nonRoutes, int debutRoute, int finRoute) {
        setNonRoutes(nonRoutes);
        setDebitRoute(debutRoute);
        setFinRoute(finRoute);
    }

    public Routes() {
    }
}
