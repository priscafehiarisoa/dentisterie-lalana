package com.example.dentisterie.model.nify;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private double valeurPrix;
    @ManyToOne
    @JoinColumn(name = "id_type_operation")
    private TypeOperation typeOperation;

//    @ManyToOne
//    @JoinColumn(name = "id_type_dent")
//    private TypeDent typeDent;
    @ManyToOne
    @JoinColumn(name = "dents_id")
    Dents dents;

    public Dents getDents() {
        return dents;
    }

    public void setDents(Dents dents) {
        this.dents = dents;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

//    public TypeDent getTypeDent() {
//        return typeDent;
//    }
//
//    public void setTypeDent(TypeDent typeDent) {
//        this.typeDent = typeDent;
//    }

    public double getValeurPrix() {
        return valeurPrix;
    }

    public void setValeurPrix(double valeurPrix) throws Exception {
        if(valeurPrix<0){
            throw new Exception(" le prix ne peux pas etre negative ");
        }
        this.valeurPrix = valeurPrix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cout(Integer id, double valeurPrix, TypeOperation typeOperation, Dents dents) throws Exception {
        setId(id);
        setValeurPrix(valeurPrix);
        setTypeOperation(typeOperation);
//        setTypeDent(typeDent);
        setDents(dents);
    }
    public Cout( double valeurPrix, TypeOperation typeOperation, Dents dents) throws Exception {

        setValeurPrix(valeurPrix);
        setTypeOperation(typeOperation);
        setDents(dents);
    }

    public Cout() {
    }

    @Override
    public String toString() {
        return "Cout{" +
                "id=" + id +
                ", valeurPrix=" + valeurPrix +
                ", typeOperation=" + typeOperation +
                ", dents=" + dents +
                '}';
    }

    public static Cout getcoutApproprie(List<Cout> coutList, EtatDent etatDent) throws Exception {
        for (int i = 0; i < coutList.size(); i++) {
            if(etatDent.getNiveauEtat()>=coutList.get(i).getTypeOperation().getStartIntervalls() && etatDent.getNiveauEtat()<=coutList.get(i).getTypeOperation().getEndIntervalls()){
                return coutList.get(i);
            }
        }
        return new Cout(0,null,etatDent.getDents());
    }

}
