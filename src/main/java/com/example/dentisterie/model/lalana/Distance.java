package com.example.dentisterie.model.lalana;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Distance {
    int distance;
    EtatRoutes etatRoutes;

    public Distance( EtatRoutes etatRoutes) {
        setEtatRoutes(etatRoutes);

        setDistance();
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setDistance() {
        int disDebut=getEtatRoutes().getDebutEtatRoutes()-getEtatRoutes().getRoutes().getDebitRoute();
        int disFin=getEtatRoutes().getRoutes().getFinRoute()-getEtatRoutes().getFinEtatRoutes();
        if(disDebut<disFin){
            setDistance(disDebut);
        }
        else {
            setDistance(disFin);
        }
    }

    public EtatRoutes getEtatRoutes() {
        return etatRoutes;
    }

    public void setEtatRoutes(EtatRoutes etatRoutes) {
        this.etatRoutes = etatRoutes;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "distance=" + distance +
                ", etatRoutes=" + etatRoutes +
                '}';
    }

    public static List<EtatRoutes> trierEtatRoutes(List<EtatRoutes> etatRoutes){
        // creer une liste de distances d'abord
        List<Distance> distances=new ArrayList<>();
        List<EtatRoutes> newEtat=new ArrayList<>();
        for (int i = 0; i < etatRoutes.size(); i++) {
            distances.add(new Distance(etatRoutes.get(i)));
        }
        Collections.sort(distances, Comparator.comparingInt(Distance::getDistance));
        for (int i = 0; i < distances.size(); i++) {
            newEtat.add(distances.get(i).getEtatRoutes());
        }
        return newEtat;
    }
}
