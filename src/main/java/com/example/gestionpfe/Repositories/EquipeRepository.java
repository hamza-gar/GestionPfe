package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends PagingAndSortingRepository<Equipe, Long> {
    Equipe findByIdEquipe(String idEquipe);

    List<Equipe> findByEtudiant(Etudiant etudiant);

    Page<Equipe> findAllBySujet_Encadrant_IdEnseignant(String idEnseignant, Pageable pageable);

    Page<Equipe> findAllBySujet_IdSujet(String idSujet, Pageable pageable);

    Page<Equipe> findAllBySujet_Encadrant_IdEnseignantAndSujet_Locked(String idEnseignant, Boolean locked, Pageable pageable);


    @Query("SELECT e FROM equipes e JOIN e.etudiant equipe_etudiant WHERE ((size(e.etudiant) = :etudiantsCount) AND (e.sujet.idSujet = :idSujet)) GROUP BY e")
    Page<Equipe> findAllByEtudiantsCountAndSujetId(@Param("etudiantsCount") int etudiantsCount, @Param("idSujet") String idSujet, Pageable pageable);
}
