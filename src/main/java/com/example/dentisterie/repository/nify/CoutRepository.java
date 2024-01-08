package com.example.dentisterie.repository.nify;

import com.example.dentisterie.model.nify.Cout;
import com.example.dentisterie.model.nify.Dents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoutRepository extends JpaRepository<Cout, Integer> {

    List<Cout> getCoutByDents(Dents dents);
}