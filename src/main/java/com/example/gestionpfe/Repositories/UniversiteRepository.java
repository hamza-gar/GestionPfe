package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Universite;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversiteRepository extends PagingAndSortingRepository<Universite, Long> {
    Universite findByNomUniversite(String nomUniversite);
    Universite findByIdUniversite(String idUniversite);

}
