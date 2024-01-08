package com.example.dentisterie.model.lalana;

import com.example.dentisterie.model.nify.Cout;
import com.example.dentisterie.model.nify.HistoriqueIntervention;
import com.example.dentisterie.repository.lalana.ConstructionRepository;
import com.example.dentisterie.repository.lalana.CoutReparationRepository;
import com.example.dentisterie.repository.lalana.EtatRoutesRepository;
import com.example.dentisterie.repository.lalana.HistoriqueReparationRepository;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class EtatRoutes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "routes_id")
    Routes routes;
    int debutEtatRoutes;
    int finEtatRoutes;
    int etatNiveau;
    LocalDateTime dateDeclarationEtat;

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public int getDebutEtatRoutes() {
        return debutEtatRoutes;
    }

    public void setDebutEtatRoutes(int debutEtatRoutes) {
        this.debutEtatRoutes = debutEtatRoutes;
    }

    public int getFinEtatRoutes() {
        return finEtatRoutes;
    }

    public void setFinEtatRoutes(int finEtatRoutes) {
        this.finEtatRoutes = finEtatRoutes;
    }

    public int getEtatNiveau() {
        return etatNiveau;
    }

    public void setEtatNiveau(int etatNiveau) {
        this.etatNiveau = etatNiveau;
    }

    public LocalDateTime getDateDeclarationEtat() {
        return dateDeclarationEtat;
    }

    public void setDateDeclarationEtat(LocalDateTime dateDeclarationEtat) {
        this.dateDeclarationEtat = dateDeclarationEtat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EtatRoutes(Integer id, Routes routes, int debutEtatRoutes, int finEtatRoutes, int etatNiveau, LocalDateTime dateDeclarationEtat) {
        setId(id);
        setRoutes(routes);
        setDebutEtatRoutes(debutEtatRoutes);
        setFinEtatRoutes(finEtatRoutes);
        setEtatNiveau(etatNiveau);
        setDateDeclarationEtat(dateDeclarationEtat);
    }

    public EtatRoutes(Routes routes, int debutEtatRoutes, int finEtatRoutes, int etatNiveau, LocalDateTime dateDeclarationEtat) {
        setRoutes(routes);
        setDebutEtatRoutes(debutEtatRoutes);
        setFinEtatRoutes(finEtatRoutes);
        setEtatNiveau(etatNiveau);
        setDateDeclarationEtat(dateDeclarationEtat);
    }

    public EtatRoutes() {
    }

    @Override
    public String toString() {
        return "EtatRoutes{" +
                "id=" + id +
                ", routes=" + routes +
                ", debutEtatRoutes=" + debutEtatRoutes +
                ", finEtatRoutes=" + finEtatRoutes +
                ", etatNiveau=" + etatNiveau +
                ", dateDeclarationEtat=" + dateDeclarationEtat +
                '}';
    }
    public static List<EtatRoutes> priorisation(Construction construction, EtatRoutesRepository etatRoutesRepository){
        List<EtatRoutes> etatRoutes= etatRoutesRepository.getEtatRoutesAReparer(construction.getDateConstruction(),construction.getRoutes());
        // tri par ordre croissant
        List<EtatRoutes> newEtat=new ArrayList<>();
        etatRoutes=Distance.trierEtatRoutes(etatRoutes);
        if(construction.getPriorite()==2){
            for (int i = etatRoutes.size(); i >0 ; i--) {
                newEtat.add(etatRoutes.get(i-1));
            }
            etatRoutes=newEtat;
        }
        return etatRoutes;
    }
    public HIstoriqueReparation reparerUneRoute(double budget, Construction construction,CoutReparationRepository coutReparationRepository){
        int totalKm= getFinEtatRoutes()-getDebutEtatRoutes();
        List<CoutReparation> coutList= coutReparationRepository.findAll();
        int etatinitiale=this.getEtatNiveau();
        int etatFinale=this.getEtatNiveau();

        double prix=0;
//        double budget= construction.getBudget();
        while (etatFinale<10){
            CoutReparation coutReparation=getCorrespondingPrice(coutList,etatFinale);
            System.out.println("cout"+coutReparation);
            System.out.println("etat initiale"+etatinitiale);
            System.out.println("etat finale"+etatFinale);
            double sanda=totalKm*coutReparation.getValeur();
            if(sanda>budget){
                System.out.println("break");
                break;
            }
            else{
                System.out.println("else");
                etatFinale+=1;
                prix+=sanda;
                budget-=sanda;
            }
        }
        HIstoriqueReparation hIstoriqueReparation=new HIstoriqueReparation(LocalDateTime.now(),this,etatinitiale,etatFinale,construction,prix,budget);
        return hIstoriqueReparation;
    }
    public CoutReparation getCorrespondingPrice(List<CoutReparation> list,int etatNiveau){
        for (int i = 0; i < list.size(); i++) {
           if(etatNiveau>=list.get(i).getDebutIntervalle() && etatNiveau<=list.get(i).getFinIntervalle()) {
               return list.get(i);
           }

        }
        return new CoutReparation(10,10,0);
    }

    public List<HIstoriqueReparation> getListeHistoriqueReparation(EtatRoutesRepository etatRoutesRepository,Construction construction,CoutReparationRepository coutReparationRepository){
        List<EtatRoutes> etatRoutes=priorisation(construction,etatRoutesRepository);
        double budget= construction.getBudget();
        List<HIstoriqueReparation> hIstoriqueReparations= new ArrayList<>();
        for (int i = 0; i < etatRoutes.size(); i++) {
            HIstoriqueReparation hIstoriqueReparation=etatRoutes.get(i).reparerUneRoute(budget,construction,coutReparationRepository);
            budget= hIstoriqueReparation.getResteBudget();
            hIstoriqueReparations.add(hIstoriqueReparation);
        }
        return hIstoriqueReparations;
    }

    public void validerConstruction(int idConstruction, EtatRoutesRepository etatRoutesRepository, CoutReparationRepository coutReparationRepository, HistoriqueReparationRepository historiqueReparationRepository, ConstructionRepository constructionRepository){
        // get construction
        Optional<Construction> optionalConstruction=constructionRepository.findById(idConstruction);
        Construction construction= new Construction();
        if(optionalConstruction.isPresent()){
            construction=optionalConstruction.get();
            List<HIstoriqueReparation> hIstoriqueReparationList=getListeHistoriqueReparation(etatRoutesRepository,construction,coutReparationRepository);
            for (int i = 0; i < hIstoriqueReparationList.size(); i++) {
                etatRoutesRepository.updateEtatRoute(hIstoriqueReparationList.get(i).getEtatRoutes().getId(),hIstoriqueReparationList.get(i).getEtatFinale());
                historiqueReparationRepository.save(hIstoriqueReparationList.get(i));
            }
            constructionRepository.updateConstruction(construction.getId());
        }
    }
}
