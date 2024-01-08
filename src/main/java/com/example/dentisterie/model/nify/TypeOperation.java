package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

@Entity
public class TypeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nomOperation;

    private int startIntervalls;
    private int endIntervalls;

    public int getStartIntervalls() {
        return startIntervalls;
    }

    public void setStartIntervalls(int startIntervalls) {
        this.startIntervalls = startIntervalls;
    }

    public int getEndIntervalls() {
        return endIntervalls;
    }

    public void setEndIntervalls(int endIntervalls) {
        this.endIntervalls = endIntervalls;
    }

    public String getNomOperation() {
        return nomOperation;
    }

    public void setNomOperation(String nomOperation) {
        this.nomOperation = nomOperation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeOperation(Integer id, String nomOperation,int debutIntervalle, int finIntervalle) {
        setId(id);
        setNomOperation(nomOperation);
        setStartIntervalls(debutIntervalle);
        setEndIntervalls(finIntervalle);
    }

    public TypeOperation() {
    }

    @Override
    public String toString() {
        return "TypeOperation{" +
                "id=" + id +
                ", nomOperation='" + nomOperation + '\'' +
                ", startIntervalls=" + startIntervalls +
                ", endIntervalls=" + endIntervalls +
                '}';
    }
}
