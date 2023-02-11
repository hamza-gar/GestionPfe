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
    Page<Sujet> findAllByEncadrant_IdEnseignantAndLocked(String idEnseignant, boolean locked, Pageable pageable);
    Page<Sujet> findAllByEncadrant_IdEnseignant(String idEnseignant, Pageable pageable);

    long countAllByLocked(boolean locked);



}
