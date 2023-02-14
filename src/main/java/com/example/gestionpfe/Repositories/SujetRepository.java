package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Sujet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepository extends PagingAndSortingRepository<Sujet, Long> {
    Sujet findByNomSujet(String nomSujet);

    Sujet findById(String id);

    Sujet findByIdSujet(String idSujet);

    Page<Sujet> findAllByLocked(boolean locked, Pageable pageable);

    long countAllByLocked(boolean locked);

    Page<Sujet> findAllByEncadrant_IdEnseignantAndLocked(String idEnseignant, boolean locked, Pageable pageable);

    Page<Sujet> findAllByEncadrant_IdEnseignant(String idEnseignant, Pageable pageable);

    long countAllByEncadrant_IdEnseignant(String idEnseignant);

    Page<Sujet> findAllByEncadrant_Departement_IdDepartement(String idDepartement, Pageable pageable);

    long countAllByEncadrant_Departement_IdDepartement(String idDepartement);

    Page<Sujet> findAllByEncadrant_Departement_Etablissement_IdEtablissement(String idEtablissement, Pageable pageable);

    long countAllByEncadrant_Departement_Etablissement_IdEtablissement(String idEtablissement);

    Page<Sujet> findAllByEncadrant_Departement_Etablissement_Universite_IdUniversite(String idUniversite, Pageable pageable);

    long countAllByEncadrant_Departement_Etablissement_Universite_IdUniversite(String idUniversite);


}
