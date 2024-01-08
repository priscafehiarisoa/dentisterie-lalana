package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

@Entity
public class Dents {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "id_type_dent")
    @ManyToOne
    private TypeDent typeDent;

    public TypeDent getTypeDent() {
        return typeDent;
    }

    public void setTypeDent(TypeDent typeDent) {
        this.typeDent = typeDent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dents(Integer id, TypeDent typeDent) {
        setId(id);
        setTypeDent(typeDent);
    }
    public Dents( TypeDent typeDent) {

        setTypeDent(typeDent);
    }

    public Dents() {
    }

    @Override
    public String toString() {
        return "Dents{" +
                "id=" + id +
                ", typeDent=" + typeDent +
                '}';
    }
}
