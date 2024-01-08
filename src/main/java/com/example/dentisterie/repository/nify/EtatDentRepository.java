package com.example.dentisterie.repository.nify;

import com.example.dentisterie.model.nify.Consultation;
import com.example.dentisterie.model.nify.EtatDent;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtatDentRepository extends JpaRepository<EtatDent, Integer> {
    @Query("select s from EtatDent s where s.niveauEtat<10 and s.consultation=:consultation")
    List<EtatDent> getEtatDentsMilaFanamboarana(@Param("consultation") Consultation consultation);


    @Query(value = "(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id" +
            "            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id<20 and consultation_id=:consultation" +
            "             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)" +
            "union all" +
            "(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id" +
            "            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id>20 and consultation_id=:consultation" +
            "             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)" +
            "union all" +
            "(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id" +
            "            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id<20 and consultation_id=:consultation" +
            "             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)" +
            "union all" +
            "(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id" +
            "            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id>20 and consultation_id=:consultation" +
            "             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)"
            ,nativeQuery = true)
    List<EtatDent> getPriorite(
            @Param("order")
            String order,
            @Param("consultation") int idConsultation);


    List<EtatDent> getEtatDentsByConsultation(Consultation consultation, Sort sort);
}
