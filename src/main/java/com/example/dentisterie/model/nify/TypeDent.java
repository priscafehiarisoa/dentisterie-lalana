package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

@Entity
public class TypeDent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String denomination;

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeDent(Integer id, String denomination) {
        setId(id);
       setDenomination(denomination);
    }
    public TypeDent(String denomination) {
       setDenomination(denomination);
    }

    public TypeDent() {
    }

    @Override
    public String toString() {
        return "TypeDent{" +
                "id=" + id +
                ", denomination='" + denomination + '\'' +
                '}';
    }
}
