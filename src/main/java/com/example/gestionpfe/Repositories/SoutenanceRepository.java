package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Soutenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SoutenanceRepository extends PagingAndSortingRepository<Soutenance,Long> {
    Soutenance findByIdSoutenance(String idSoutenance);
    Soutenance findByDateSoutenance(Date dateSoutenance);
    Soutenance findBySujet_IdSujet(String salleSoutenance);

    @Query("SELECT s FROM soutenances s JOIN s.sujet su ON s.sujet.id = su.id JOIN su.encadrant e ON su.encadrant.idEnseignant = e.idEnseignant WHERE e.idEnseignant = :idEncadrant OR EXISTS (SELECT j FROM s.jurys j WHERE j.enseignant.idEnseignant = :encadrantId)")
    Page<Soutenance> findAllBySujet_Encadrant_IdEnseignantOrJurys_Enseignant_IdEnseignant(@Param("idEncadrant") String idEncadrant,@Param("encadrantId") String encadrantId, Pageable pageable);

    @Query("SELECT s FROM soutenances s JOIN s.jurys jury WHERE jury.enseignant.email = :mailJury")
    Page<Soutenance> findAllByJuryEmail(@Param("mailJury") String mailJury, Pageable pageable);


}
