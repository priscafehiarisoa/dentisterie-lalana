create view prix_operations
            (id_type_operation, id_type_dent, valeur_prix, consultation_id, etat_id, dents_id, idetatdent) as
SELECT c.id_type_operation,
       c.id_type_dent,
       c.valeur_prix,
       etat_dent.consultation_id,
       etat_dent.dents_id,
       etat_dent.id AS idetatdent
FROM etat_dent
         JOIN dents d ON d.id = etat_dent.dents_id
         JOIN cout c ON d.id_type_dent = c.id_type_dent


-- ==============================
select * from etat_dent
select
    etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id,id_type_dent
from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3

order by niveau_etat asc, dents_id asc ,id_type_dent asc
-- =======================================
(select
    etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id,id_type_dent
from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id<20

order by niveau_etat asc, id_type_dent asc,dents_id asc )
union all
(select
     etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id,id_type_dent
 from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id>20

 order by niveau_etat asc, id_type_dent asc,dents_id asc)
union all
(select
     etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id,id_type_dent
 from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id<20

 order by niveau_etat asc, id_type_dent asc,dents_id asc)
union all
(select
     etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id,id_type_dent
 from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id>20

 order by niveau_etat asc, id_type_dent asc,dents_id asc)

-- ========================

(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id, id_type_dent
            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id<20 and consultation_id=:consultation
             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)
union all
(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id, id_type_dent
            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat<=3 and dents_id>20 and consultation_id=:consultation
             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)
union all
(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id, id_type_dent
            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id<20 and consultation_id=:consultation
             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)
union all
(select etat_dent.niveau_etat,etat_dent.id,etat_dent.dents_id,etat_dent.consultation_id, id_type_dent
            from etat_dent  join public.dents d on d.id = etat_dent.dents_id where niveau_etat>3 and niveau_etat<10 and dents_id>20 and consultation_id=:consultation
             order by CASE WHEN :order = 'ASC' THEN id_type_dent END ASC, CASE WHEN :order = 'DESC' THEN id_type_dent END DESC, CASE WHEN :order = 'ASC' THEN niveau_etat END ASC, CASE WHEN :order = 'DESC' THEN niveau_etat END DESC, CASE WHEN :order = 'ASC' THEN dents_id END ASC, CASE WHEN :order = 'DESC' THEN dents_id END DESC)



select * from etat_routes as s where s.etat_niveau<10 and s.date_declaration_etat <=current_timestamp and s.routes_id=:routes

select * from etat_routes where etat_niveau<10 and date_declaration_etat > current_timestamp