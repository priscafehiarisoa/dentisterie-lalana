package com.example.dentisterie.controller;

import com.example.dentisterie.model.lalana.Construction;
import com.example.dentisterie.model.lalana.EtatRoutes;
import com.example.dentisterie.model.lalana.HIstoriqueReparation;
import com.example.dentisterie.model.lalana.Routes;
import com.example.dentisterie.repository.lalana.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ConstructionController {

    private final RoutesRepository routesRepository;
    private final EtatRoutesRepository etatRoutesRepository;
    private final ConstructionRepository constructionRepository;
    private final CoutReparationRepository coutReparationRepository;
    private final HistoriqueReparationRepository historiqueReparationRepository;

    public ConstructionController(RoutesRepository routesRepository,
                                  EtatRoutesRepository etatRoutesRepository,
                                  ConstructionRepository constructionRepository,
                                  CoutReparationRepository coutReparationRepository,
                                  HistoriqueReparationRepository historiqueReparationRepository) {
        this.routesRepository = routesRepository;
        this.etatRoutesRepository = etatRoutesRepository;
        this.constructionRepository = constructionRepository;
        this.coutReparationRepository = coutReparationRepository;
        this.historiqueReparationRepository = historiqueReparationRepository;
    }

    @GetMapping("getFormEtatRoutes")
    public String getFormEtatRoutes(Model  model){
        List<Routes> routesList=routesRepository.findAll();
        model.addAttribute("routes",routesList);
        return "construction/formEtatRoutes";
    }
    @GetMapping("getFormConstruction")
    public String getFormConstruction(Model  model){
        List<Routes> routesList=routesRepository.findAll();
        model.addAttribute("routes",routesList);
        return "construction/FormConstruction";
    }

    @PostMapping("/submitEtatRoutes")
    public Object submitEtatRoutes(Model model,
                                   @RequestParam("idRoutes") Integer idRoutes,
                                   @RequestParam("debut") Integer debut,
                                   @RequestParam("fin") Integer fin,
                                   @RequestParam("etatNiveau") Integer etatNiveau ){
        // recupérer la route
        Optional<Routes>  routesOptional= routesRepository.findById(idRoutes);
        Routes routes=new Routes();
        String returnView="construction/formEtatRoutes";
        if(routesOptional.isPresent()){
            routes=routesOptional.get();
            // verifier que debut et fin est entre les debut et fins de routes
            if(debut<routes.getDebitRoute() || fin <routes.getDebitRoute() || debut>routes.getFinRoute() || fin>routes.getFinRoute()){
                model.addAttribute("erreur","les intervalles ne correspondent pas à la route");
            }
            // verifier que les données ne se chevauchent pas
            else{
                if(etatNiveau<0 || etatNiveau>10){
                    model.addAttribute("erreur","les intervalles de niveau ne coincident pas");

                }
                else{
                    EtatRoutes etatRoutes= new EtatRoutes();
                    etatRoutes.setRoutes(routes);
                    etatRoutes.setDebutEtatRoutes(debut);
                    etatRoutes.setFinEtatRoutes(fin);
                    etatRoutes.setEtatNiveau(etatNiveau);
                    etatRoutes.setDateDeclarationEtat(LocalDateTime.now());
                    etatRoutesRepository.save(etatRoutes);
                }
            }
        }
        else{
            model.addAttribute("erreur","la route numero "+idRoutes+" selectionné est introuvable");
        }
        final RedirectView redirectView = new RedirectView("/getFormEtatRoutes", true);

        return returnView;
    }

    @PostMapping("/submitConstruction")
    public Object submitConstruction(Model model,
                                     @RequestParam("idRoutes") Integer idRoutes,
                                     @RequestParam("budget") double budget,
                                     @RequestParam("priorite") Integer priorite){
        Routes routes= new Routes();
        Optional<Routes> routesOptional= routesRepository.findById(idRoutes);
        String returnView="construction/formEtatRoutes";

        if(routesOptional.isPresent()){
            routes=routesOptional.get();
            if(budget>0){
                Construction construction= new Construction();
                construction.setDateConstruction(LocalDateTime.now());
                construction.setBudget(budget);
                construction.setRoutes(routes);
                construction.setPriorite(priorite);
                constructionRepository.save(construction);
            }
        }
        else{
            model.addAttribute("erreur","la route numero "+idRoutes+" selectionné est introuvable");

        }
        final RedirectView redirectView = new RedirectView("/getFormConstruction", true);
        return redirectView;
    }

    @GetMapping("/listConstruction")
    public String listConstruction(Model model){
        model.addAttribute("construction",constructionRepository.findAll());
        return "construction/listConstruction";
    }
    @GetMapping("/factureConstruction/{id}")
    public String factureConstruction(Model model, @PathVariable("id")Integer idConstruction){
        Optional<Construction> constructionOptional=constructionRepository.findById(idConstruction);

        Construction construction=new Construction();
        if(constructionOptional.isPresent()){
            construction=constructionOptional.get();
            if(construction.getEtat()==1){
                List<HIstoriqueReparation> hIstoriqueReparations=historiqueReparationRepository.getHIstoriqueReparationByConstruction(construction);
                List<EtatRoutes> etatRoutes=new ArrayList<>();
                model.addAttribute("historique",hIstoriqueReparations);
            }
            else{
                //construction
                // liste des reparations a faire
                model.addAttribute("etatRoutes",etatRoutesRepository.getEtatRoutesAReparer(construction.getDateConstruction(),construction.getRoutes()));
                // liste des historiques
                model.addAttribute("historique",new EtatRoutes().getListeHistoriqueReparation(etatRoutesRepository,construction,coutReparationRepository));

            }
            model.addAttribute("construction",construction);

        }
        return "construction/FactureConstruction";
    }

    @GetMapping("/validerConstruction/{id}")
    public void validerConstruction(@PathVariable("id") Integer idConstruction){
        new EtatRoutes().validerConstruction(idConstruction,etatRoutesRepository,coutReparationRepository,historiqueReparationRepository,constructionRepository);
    }
}
