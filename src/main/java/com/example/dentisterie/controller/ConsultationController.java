package com.example.dentisterie.controller;

import com.example.dentisterie.model.nify.*;
import com.example.dentisterie.repository.nify.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ConsultationController {

    private final DentsRepository dentsRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final EtatDentRepository etatDentRepository;
//    private final PrixOperationRepository prixOperationRepository;
    private final TypeOperationRepository typeOperationRepository;
    private final CoutRepository coutRepository;

    public ConsultationController(DentsRepository dentsRepository,
                                  PatientRepository patientRepository,
                                  ConsultationRepository consultationRepository,
                                  EtatDentRepository etatDentRepository,
//                                  PrixOperationRepository prixOperationRepository,
                                  TypeOperationRepository typeOperationRepository,
                                  CoutRepository coutRepository) {
        this.dentsRepository = dentsRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.etatDentRepository = etatDentRepository;
//        this.prixOperationRepository = prixOperationRepository;
        this.typeOperationRepository = typeOperationRepository;
        this.coutRepository = coutRepository;
    }

    @GetMapping({"/","/consultation"})
    public String formConsultation (Model model){
        List<Dents> listeDents= dentsRepository.findAll();
        model.addAttribute("dents",listeDents);

        System.out.println("1");
        model.addAttribute("patient",patientRepository.findAll());
        patientRepository.findAll().forEach(System.out::println);

        return "consultation/formConsultation";
    }

    @PostMapping("/submitConsultation")
    public Object submitForm(@RequestParam("patient") Integer patientId,
                             @RequestParam("etatDents") List<String> etatsDentsIds,
                             @RequestParam("budget") double budget,
                             @RequestParam("priorite") int priorite,
                             Model model) {
        final RedirectView redirectView = new RedirectView("/consultation", true);
        try{
            Patient patient = new Patient().getPatientFromId(patientRepository,patientId);
            Consultation consultation= new Consultation(patient);
            consultation.setPriorite(priorite);
            consultation.setBudget(budget);
            consultationRepository.save(consultation);
            List<EtatDent> etatDentList= new ArrayList<>();
            for (int i = 0; i < etatsDentsIds.size(); i++) {
                etatDentList.add(consultation.setEtatDentFromString(etatsDentsIds.get(i),dentsRepository));
            }
            etatDentRepository.saveAll(etatDentList);

            // affichage de la page d'historique

                // etat initiale des dents
                model.addAttribute("etatdentInitial",etatDentList);
                // get historique des reparations
                List<HistoriqueIntervention> historiqueInterventions= new EtatDent().getListOfIntervention(coutRepository,etatDentRepository,consultation,consultation.getBudget());
                model.addAttribute("historique",historiqueInterventions);
                List<EtatDent> etatDentList1=getEtatFinal(etatDentList,historiqueInterventions);
//                model.addAttribute("etatdentfinal",etatDentList1 );

        }catch (Exception e){
            System.out.println("erreur : "+e.getMessage() +"patient : "+patientId);
            model.addAttribute("erreur",e.getMessage());
            return redirectView;
        }
        return "consultation/factureConsultation";
    }

    @GetMapping("/listConsultation")
    public String listeConsultation(Model model){
        model.addAttribute("consultation",consultationRepository.findAll());
        return "consultation/listeConsultation";
    }

    @GetMapping("factureConsultations/{id}")
    public String getFacture(Model model , @PathVariable Integer id) throws Exception {
//        prixOperationRepository

//        Consultation consultation= new Consultation().getConsultationById(id,consultationRepository,etatDentRepository);
        Optional<Consultation>consultationOptional=consultationRepository.findById(id);
        if(consultationOptional.isPresent()) {
            Consultation consultation =consultationOptional.get();
            List < EtatDent > etatDentList = consultation.getListeDents(etatDentRepository);
            List<HistoriqueIntervention> historiqueInterventions = new EtatDent().getListOfIntervention(coutRepository, etatDentRepository, consultation, consultation.getBudget());
            System.out.println("cons : " + consultation);
            etatDentList.forEach(System.out::println);
            historiqueInterventions.forEach(System.out::println);
            System.out.println("lalalere ");
            List<EtatDent> l2=etatDentList;

            model.addAttribute("etatdentInitial",etatDentList);
            model.addAttribute("historique",historiqueInterventions);
            model.addAttribute("etatdentfinal", getEtatFinal(l2,historiqueInterventions));
        }

        return "consultation/factureConsultation";
    }
    public List<EtatDent> getEtatFinal(List<EtatDent> etatDentList,List<HistoriqueIntervention> historiqueInterventions){
        List<EtatDent> etatDentList1=new ArrayList<>();
        for (int i = 0; i < etatDentList.size(); i++) {
            EtatDent etatDent=new EtatDent();
            int etat=etatDentList.get(i).getNiveauEtat();
            for (int j = 0; j < historiqueInterventions.size(); j++) {
                if(Objects.equals(etatDentList.get(i).getDents().getId(), historiqueInterventions.get(j).getEtatDent().getDents().getId())){
                   etat=historiqueInterventions.get(j).getEtatFinale();
                }
            }
            etatDent.setId(etatDentList.get(i).getId());
            etatDent.setNiveauEtat(etat);
            etatDent.setConsultation(etatDentList.get(i).getConsultation());
            etatDent.setDents(etatDentList.get(i).getDents());
            etatDentList1.add(etatDent);
        }
        return etatDentList1;
    }

    @GetMapping("/alea")
    public String alea(Model model){
        model.addAttribute("patient",patientRepository.findAll());
        return "consultation/alea1";
    }

    @PostMapping("formAlea")
    public Object formAlea(Model model,
                           @RequestParam("patient") Integer idPatient,
                           @RequestParam("priorite") Integer priorite,
                           @RequestParam("budget") double budget,
                           @RequestParam("dent") String dent ,
                           @RequestParam("etat") String etat
                           ){
        Optional<Patient> patientOptional= patientRepository.findById(idPatient);
        if(patientOptional.isPresent()) {
            Consultation consultation = new Consultation(patientOptional.get());
            consultation.setBudget(budget);
            consultation.setPriorite(priorite);

            consultationRepository.save(consultation);
            //enlever le d
            dent = dent.replace("d", "");
            dent = dent.replace("D", "");
            dent = dent.replace(" ", "");

            etat= etat.replace(" ","");

            List<Dents> listIdDents = new ArrayList<>();
            List<EtatDent> etatDentList=new ArrayList<>();
   // cas où d1-d3
            if (dent.contains("-") && !dent.contains(",")) {
                String[] iddents = dent.split("-");
                if (iddents.length > 0) {
                    for (int i = Integer.parseInt(iddents[0]); i < Integer.parseInt(iddents[1]) - Integer.parseInt(iddents[0]) + 1; i++) {
                        if(i>16 && i<21){
                            continue;
                        }
                        else{
                            Optional<Dents> dentsOptional= dentsRepository.findById(i);
                            if(dentsOptional.isPresent()){
                                listIdDents.add(dentsOptional.get());
                            }
                        }

                    }
                }
                int niveau = Integer.parseInt(etat);
                for (int i = 0; i < listIdDents.size(); i++) {
                    EtatDent etatDent= new EtatDent(listIdDents.get(i),niveau);
                    etatDent.setConsultation(consultation);
                    etatDentList.add(etatDent);
                }

            }
//        cas où d1,d2,d3
            else if (dent.contains(",") && !dent.contains("-") && etat.contains(",")) {
                String[] iddents = dent.split(",");
                String[] valeur= etat.split(",");
                if(iddents.length>0 && valeur.length>0 && iddents.length==valeur.length){
                    for (int i = 0; i < iddents.length; i++) {
                        // create dents
                        Optional<Dents> dentsOptional= dentsRepository.findById(i);
                        if(dentsOptional.isPresent()){
                            EtatDent etatDent= new EtatDent(dentsOptional.get(),Integer.parseInt(valeur[i]));
                            etatDent.setConsultation(consultation);
                            etatDentList.add(etatDent);
                        }
                    }
                }

            }
            // save etat dent
            etatDentRepository.saveAll(etatDentList);
        }
        final RedirectView redirectView = new RedirectView("/listConsultation", true);

        return redirectView;

    }
}
