package com.example.dentisterie;

import com.example.dentisterie.model.lalana.*;
import com.example.dentisterie.model.nify.*;
import com.example.dentisterie.repository.lalana.ConstructionRepository;
import com.example.dentisterie.repository.lalana.CoutReparationRepository;
import com.example.dentisterie.repository.lalana.EtatRoutesRepository;
import com.example.dentisterie.repository.lalana.RoutesRepository;
import com.example.dentisterie.repository.nify.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
public class Main {

    @Bean
    CommandLineRunner commandLineRunner(
                                        PatientRepository patientRepository,
                                        TypeDentRepository typeDentRepository,
                                        DentsRepository dentsRepository,
                                        TypeOperationRepository typeOperationRepository,
                                        CoutRepository coutRepository,
                                        ConsultationRepository consultationRepository,
                                        EtatDentRepository etatDentRepository,
                                        EtatRoutesRepository etatRoutesRepository,
                                        RoutesRepository routesRepository,
                                        CoutReparationRepository coutReparationRepository,
                                        ConstructionRepository constructionRepository){
        return args -> {


            List<Patient> listePatient=new ArrayList<>();
            listePatient.add(new Patient(1,"fehiarisoa"));
            listePatient.add(new Patient(2,"prisca"));

            patientRepository.saveAll(listePatient);
            List<TypeDent> typeDents= new ArrayList<>();
            typeDents.add(new TypeDent(1,"incisive centrale"));
            typeDents.add(new TypeDent(2,"incisive laterale"));
            typeDents.add(new TypeDent(3,"canine"));
            typeDents.add(new TypeDent(4,"premiere prémolaire"));
            typeDents.add(new TypeDent(5,"deuxième prémolaire"));
            typeDents.add(new TypeDent(6,"première molaire"));
            typeDents.add(new TypeDent(7,"deuxième molaire"));
            typeDents.add(new TypeDent(8,"troisième molaire"));
            typeDentRepository.saveAll(typeDents);
            List<Dents> listeDents= new ArrayList<>();
            listeDents.add(new Dents(1,typeDents.get(7)));
            listeDents.add(new Dents(2,typeDents.get(6)));
            listeDents.add(new Dents(3,typeDents.get(5)));
            listeDents.add(new Dents(4,typeDents.get(4)));
            listeDents.add(new Dents(5,typeDents.get(3)));
            listeDents.add(new Dents(6,typeDents.get(2)));
            listeDents.add(new Dents(7,typeDents.get(1)));
            listeDents.add(new Dents(8,typeDents.get(0)));
            listeDents.add(new Dents(9,typeDents.get(0)));
            listeDents.add(new Dents(10,typeDents.get(1)));
            listeDents.add(new Dents(11,typeDents.get(2)));
            listeDents.add(new Dents(12,typeDents.get(3)));
            listeDents.add(new Dents(13,typeDents.get(4)));
            listeDents.add(new Dents(14,typeDents.get(5)));
            listeDents.add(new Dents(15,typeDents.get(6)));
            listeDents.add(new Dents(16,typeDents.get(7)));
            listeDents.add(new Dents(21,typeDents.get(7)));
            listeDents.add(new Dents(22,typeDents.get(6)));
            listeDents.add(new Dents(23,typeDents.get(5)));
            listeDents.add(new Dents(24,typeDents.get(4)));
            listeDents.add(new Dents(25,typeDents.get(3)));
            listeDents.add(new Dents(26,typeDents.get(2)));
            listeDents.add(new Dents(27,typeDents.get(1)));
            listeDents.add(new Dents(28,typeDents.get(0)));
            listeDents.add(new Dents(29,typeDents.get(0)));
            listeDents.add(new Dents(30,typeDents.get(1)));
            listeDents.add(new Dents(31,typeDents.get(2)));
            listeDents.add(new Dents(32,typeDents.get(3)));
            listeDents.add(new Dents(33,typeDents.get(4)));
            listeDents.add(new Dents(34,typeDents.get(5)));
            listeDents.add(new Dents(35,typeDents.get(6)));
            listeDents.add(new Dents(36,typeDents.get(7)));
            dentsRepository.saveAll(listeDents);

            List<TypeOperation> operationList = new ArrayList<>();
            operationList.add(new TypeOperation(1,"reparation",4,7));
            operationList.add(new TypeOperation(2,"remplacement",0,0));
            operationList.add(new TypeOperation(3,"nettoyage",8,9));
            operationList.add(new TypeOperation(4,"extraction",1,3));
            typeOperationRepository.saveAll(operationList);

            List<Cout> coutList=new ArrayList<>();
            //incisive centrale
            coutList.add(new Cout(1,2000,operationList.get(0),listeDents.get(0) ));
            coutList.add(new Cout(2,2500,operationList.get(1),listeDents.get(0) ));
            coutList.add(new Cout(17,2000,operationList.get(2),listeDents.get(0) ));
            coutList.add(new Cout(18,2500,operationList.get(3),listeDents.get(0) ));
//            incisive laterale
            coutList.add(new Cout(3,2000,operationList.get(0),listeDents.get(1) ));
            coutList.add(new Cout(4,2500,operationList.get(1),listeDents.get(1) ));
            coutList.add(new Cout(19,2000,operationList.get(2),listeDents.get(1) ));
            coutList.add(new Cout(20,2500,operationList.get(3),listeDents.get(1) ));
//            canine
            coutList.add(new Cout(5,2000,operationList.get(0),listeDents.get(2) ));
            coutList.add(new Cout(6,2500,operationList.get(1),listeDents.get(2) ));
            coutList.add(new Cout(21,2000,operationList.get(2),listeDents.get(2) ));
            coutList.add(new Cout(22,2500,operationList.get(3),listeDents.get(2) ));
//            premiere prémolaire
            coutList.add(new Cout(7,2000,operationList.get(0),listeDents.get(3) ));
            coutList.add(new Cout(8,2500,operationList.get(1),listeDents.get(3) ));
            coutList.add(new Cout(23,2000,operationList.get(2),listeDents.get(3) ));
            coutList.add(new Cout(24,2500,operationList.get(3),listeDents.get(3) ));
//            deuxième prémolaire
            coutList.add(new Cout(9,2000,operationList.get(0),listeDents.get(4) ));
            coutList.add(new Cout(10,2500,operationList.get(1),listeDents.get(4) ));
            coutList.add(new Cout(25,2000,operationList.get(2),listeDents.get(4) ));
            coutList.add(new Cout(26,2500,operationList.get(3),listeDents.get(4) ));
//            première molaire
            coutList.add(new Cout(11,2000,operationList.get(0),listeDents.get(5) ));
            coutList.add(new Cout(12,2500,operationList.get(1),listeDents.get(5) ));
            coutList.add(new Cout(27,2000,operationList.get(2),listeDents.get(5) ));
            coutList.add(new Cout(28,2500,operationList.get(3),listeDents.get(5) ));
//            seconde molaire
            coutList.add(new Cout(13,2000,operationList.get(0),listeDents.get(6) ));
            coutList.add(new Cout(14,2500,operationList.get(1),listeDents.get(6) ));
            coutList.add(new Cout(29,2000,operationList.get(2),listeDents.get(6) ));
            coutList.add(new Cout(30,2500,operationList.get(3),listeDents.get(6) ));
//            troisieme molaire
            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(7) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(7) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(7) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(7) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(8) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(8) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(8) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(8) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(9) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(9) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(9) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(9) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(10) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(10) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(10) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(10) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(11) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(11) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(11) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(11) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(12) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(12) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(12) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(12) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(13) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(13) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(13) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(13) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(14) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(14) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(14) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(14) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(15) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(15) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(15) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(15) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(16) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(16) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(16) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(16) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(17) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(17) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(17) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(17) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(18) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(18) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(18) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(18) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(19) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(19) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(19) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(19) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(20) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(20) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(20) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(20) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(21) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(21) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(21) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(21) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(22) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(22) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(22) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(22) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(23) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(23) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(23) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(23) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(24) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(24) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(24) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(24) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(25) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(25) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(25) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(25) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(26) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(26) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(26) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(26) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(27) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(27) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(27) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(27) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(28) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(28) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(28) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(28) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(29) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(29) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(29) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(29) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(30) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(30) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(30) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(30) ));

            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(31) ));
            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(31) ));
            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(31) ));
            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(31) ));

//            coutList.add(new Cout(15,2000,operationList.get(0),listeDents.get(32) ));
//            coutList.add(new Cout(16,2500,operationList.get(1),listeDents.get(32) ));
//            coutList.add(new Cout(31,2000,operationList.get(2),listeDents.get(32) ));
//            coutList.add(new Cout(32,2500,operationList.get(3),listeDents.get(32) ));

            //rearranger l'id
            for (int i = 0; i < coutList.size(); i++) {
                coutList.get(i).setId(i+1);
            }

            coutRepository.saveAll(coutList);

            Consultation consultation= new Consultation().getConsultationById(1,consultationRepository,etatDentRepository);
            System.out.println("lui \n"+ consultation);

            List<EtatDent> etatDentList= etatDentRepository.findAll();
                List<Routes> routesList=new ArrayList<>();
                routesList.add(new Routes(1,"RN1",0,200));
                routesList.add(new Routes(2,"RN2",0,200));
                routesRepository.saveAll(routesList);

                List<CoutReparation> coutReparations= new ArrayList<>();
                coutReparations.add(new CoutReparation(1,0,0,2000));
                coutReparations.add(new CoutReparation(2,1,3,2000));
                coutReparations.add(new CoutReparation(3,4,7,2000));
                coutReparations.add(new CoutReparation(4,8,9,2000));
                coutReparationRepository.saveAll(coutReparations);
                Construction construction= constructionRepository.findById(2).get();
                List<HIstoriqueReparation> hIstoriqueReparations=new EtatRoutes().getListeHistoriqueReparation(etatRoutesRepository,construction,coutReparationRepository);
                System.out.println("lolita");
                hIstoriqueReparations.forEach(System.out::println);
        };
    }
}
