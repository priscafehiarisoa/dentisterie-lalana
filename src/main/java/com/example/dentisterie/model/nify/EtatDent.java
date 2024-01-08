package com.example.dentisterie.model.nify;

import com.example.dentisterie.repository.nify.CoutRepository;
import com.example.dentisterie.repository.nify.EtatDentRepository;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EtatDent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "dents_id")
    private Dents dents;
//    @ManyToOne
//    @JoinColumn(name = "etat_id")
//    private Etat etat;
//
    private int niveauEtat;


    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

//    public Etat getEtat() {
//        return etat;
//    }
//
//    public void setEtat(Etat etat) {
//        this.etat = etat;
//    }


    public int getNiveauEtat() {
        return niveauEtat;
    }

    public void setNiveauEtat(int niveauEtat) {
        this.niveauEtat = niveauEtat;
    }

    public Dents getDents() {
        return dents;
    }

    public void setDents(Dents dents) {
        this.dents = dents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EtatDent(Integer id, Dents dents,int niveauEtat) {
        setId(id);
        setDents(dents);
        setNiveauEtat(niveauEtat);
    }
    public EtatDent( Dents dents, int niveauEtat ) {
        setDents(dents);
       setNiveauEtat(niveauEtat);
    }

    public EtatDent() {
    }

    // get type Operation : en fonction an'ny etat an'le nify
//    public TypeOperation getTypeOperation (TypeOperationRepository typeOperationRepository) throws Exception {
//        Optional<TypeOperation> typeOperation;
//        TypeOperation tpo=new TypeOperation();
//        if(this.getEtat().getId()==1){
//            throw new Exception("dents en bonne santé");
//        }
//        // operation = remplacement
//        else if (getEtat().getId()==2){
//            typeOperation  = typeOperationRepository.findById(2);
//            if(typeOperation.isPresent()){
//                tpo= typeOperation.get();
//            }
//        }
//        // operation = soins
//        else if (getEtat().getId()==3){
//            typeOperation= typeOperationRepository.findById(1);
//            if(typeOperation.isPresent()){
//                tpo= typeOperation.get();
//            }
//        }
//        return tpo;
//    }


    @Override
    public String toString() {
        return "EtatDent{" +
                "id=" + id +
                ", dents=" + dents +
                ", niveauEtat=" + niveauEtat +
                ", consultation=" + consultation +
                '}';
    }

    public HistoriqueIntervention calculPrixTraitementEtCreationHistorique(CoutRepository coutRepository, double limiteBudget) throws Exception {
        List<Cout> listeCout=coutRepository.getCoutByDents(this.getDents());
        int etatInitiale=this.getNiveauEtat();
        int etatFinale=this.getNiveauEtat();
        double prixReparation=0;
        while (etatFinale<10){
            Cout cout= Cout.getcoutApproprie(listeCout,this);
            if(limiteBudget>=cout.getValeurPrix()){
                // si c'est un 0 => banga
                if(etatInitiale==0){
                    etatFinale=10;
                    prixReparation+=cout.getValeurPrix();
                    limiteBudget-=cout.getValeurPrix();
                }
                // si 1-3 => mila alàna
                else if(etatInitiale>=1 && etatInitiale<=3){
                    etatFinale=0;
                    prixReparation+=cout.getValeurPrix();
                    limiteBudget-=cout.getValeurPrix();
                    // raha ampy ny vola de soloina
                    EtatDent newEtatDent=new EtatDent();
                    newEtatDent.setId(this.getId());
                    newEtatDent.setNiveauEtat(etatFinale);
                    newEtatDent.setConsultation(getConsultation());
                    newEtatDent.setDents(getDents());
                    cout= Cout.getcoutApproprie(listeCout,newEtatDent);
                    if(cout.getValeurPrix()<=limiteBudget){
                        etatFinale=10;
                        prixReparation+=cout.getValeurPrix();
                        limiteBudget-=cout.getValeurPrix();
                    }
                } else if (etatInitiale==10) {
                    continue;
                } else{
                    etatFinale+=1;
                    prixReparation+=cout.getValeurPrix();
                    limiteBudget-=cout.getValeurPrix();
                }
                // sinon ampiana ray fotsiny
            }
            else{
//                throw new Exception("tsy ampy vola ");
                break;
            }
        }
        System.out.println("reste: "+limiteBudget);
        HistoriqueIntervention historiqueIntervention= new HistoriqueIntervention(etatInitiale,etatFinale,this, LocalDateTime.now());
        historiqueIntervention.setPrixIntervention(prixReparation);
        historiqueIntervention.setResteBudget(limiteBudget);
        return historiqueIntervention;
    }

    public List<HistoriqueIntervention> getListOfIntervention (CoutRepository coutRepository , EtatDentRepository etatDentRepository,Consultation consultation, double budget) throws Exception {
        List<HistoriqueIntervention> interventionList=new ArrayList<>();
        List<EtatDent> etatDentList1=consultation.prioriser(etatDentRepository);
        System.out.println("priorisation ");
        etatDentList1.forEach(System.out::println);
        for (int i = 0; i < etatDentList1.size(); i++) {
            HistoriqueIntervention historiqueIntervention= etatDentList1.get(i).calculPrixTraitementEtCreationHistorique(coutRepository,budget );
            budget= historiqueIntervention.getResteBudget();
            if(historiqueIntervention.getPrixIntervention()==0){
                continue;
            }
            else {
                interventionList.add(historiqueIntervention);
            }
//            System.out.println(consultation.getBudget());
        }
        return interventionList;
    }
    public double getRemainingBudget(List<EtatDent> etatDentList1, CoutRepository coutRepository, double budget) throws Exception {
        for (int i = 0; i < etatDentList1.size(); i++) {
            HistoriqueIntervention historiqueIntervention= etatDentList1.get(i).calculPrixTraitementEtCreationHistorique(coutRepository,budget );
            budget= historiqueIntervention.getResteBudget();
            System.out.println(historiqueIntervention);
//            System.out.println(consultation.getBudget());
        }
        return budget;
    }
}
