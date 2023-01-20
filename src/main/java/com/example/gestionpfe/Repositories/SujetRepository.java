package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Sujet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepository extends PagingAndSortingRepository<Sujet,Long> {
    Sujet findByNomSujet(String nomSujet);
    Sujet findById(String id);
}
