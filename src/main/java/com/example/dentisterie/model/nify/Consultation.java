package com.example.dentisterie.model.nify;

import com.example.dentisterie.repository.nify.ConsultationRepository;
import com.example.dentisterie.repository.nify.DentsRepository;
import com.example.dentisterie.repository.nify.EtatDentRepository;
import com.example.dentisterie.repository.nify.TypeOperationRepository;
import jakarta.persistence.*;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    LocalDateTime dateConsultation;
    @Transient
    List<EtatDent> listeDents;
    double budget;
    int priorite;

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

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EtatDent> getListeDents() {
        return listeDents;
    }
    public List<EtatDent> getListeDents(EtatDentRepository etatDentRepository) {
        String order = "ASC";  // or "DESC"
        Sort sort = Sort.by(Sort.Direction.ASC,  "dents_id");


        return etatDentRepository.getEtatDentsByConsultation(this,sort);
    }

    public void setListeDents(List<EtatDent> listeDents) {
        this.listeDents = listeDents;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Consultation(Integer id, Patient patient, LocalDateTime dateConsultation, List<EtatDent> listeDents) {
        setId(id);
        setPatient(patient);
        setDateConsultation(dateConsultation);
        setListeDents(listeDents);
    }
    public Consultation(Integer id, Patient patient) {
        setId(id);
        setPatient(patient);
        setDateConsultation(LocalDateTime.now());
    }
    public Consultation( Patient patient) {
        setPatient(patient);
        setDateConsultation(LocalDateTime.now());
    }

    public EtatDent setEtatDentFromString(String stringFromForm, DentsRepository dentsRepository){
        String [] etat= stringFromForm.split(",");
        Optional<Dents> dentsOptional= dentsRepository.findById(Integer.valueOf(etat[0]));
        Dents dents=new Dents();
        if(dentsOptional.isPresent()){
            dents=dentsOptional.get();
        }
        EtatDent etatDent= new EtatDent(dents,Integer.parseInt(etat[1]));
        etatDent.setConsultation(this);
        return etatDent;
    }

    public Consultation getConsultationById(int id, ConsultationRepository consultationRepository, EtatDentRepository etatDentRepository){
        Optional<Consultation> consultationOptional= consultationRepository.findById(id);
        Consultation consultation=new Consultation();
        if(consultationOptional.isPresent()){
            consultation= consultationOptional.get();
            List<EtatDent> listEtatDent = etatDentRepository.getEtatDentsMilaFanamboarana(consultation);
            consultation.setListeDents(listEtatDent);
        }
        return consultation;
    }

    public Consultation() {
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", patient=" + patient +
                ", dateConsultation=" + dateConsultation +
                ", listeDents=" + listeDents +
                '}';
    }

    public static Integer getTypeOperationbyPriorite(int priorite){
        //beaute
        if(priorite==1){
            // remplacement
            return 2;
        }
        else{
            // traitement
            return 1;
        }
    }

    public Facture calculFactureParPriorite(TypeOperationRepository typeOperationRepository) throws Exception {
//        List<EtatDent> priorite=new ArrayList<>();
//        List<EtatDent> nonPriorite = new ArrayList<>();
//        int idTypeoperationPriorite=getTypeOperationbyPriorite(this.getPriorite());
//        for (int i = 0; i < this.getListeDents().size(); i++) {
//            try {
//                TypeOperation typeOperation = getListeDents().get(i).getTypeOperation(typeOperationRepository);
//                if (typeOperation.getId()==idTypeoperationPriorite){
//                    priorite.add(this.getListeDents().get(i));
//                }
//                else{
//                    nonPriorite.add(this.getListeDents().get(i));
//                }
//            }catch (Exception e){
//                System.out.println("erreur : "+e.getMessage());
//            }
//        }
//        // calcul des priorités d'abord
//        for (int i = 0; i < priorite.size(); i++) {
//
//        }
        return new Facture();
    }

    // prioriser les dents en fonction des besoins de l'utilisateur
    public List<EtatDent> prioriser (EtatDentRepository etatDentRepository){
        String order="";
        //beaute
        if(this.getPriorite()==1){
            order="ASC";
        }
        else if(this.getPriorite()==2){
            order="DESC";
        }
        return etatDentRepository.getPriorite(
                order,
                this.getId());

    }

}
