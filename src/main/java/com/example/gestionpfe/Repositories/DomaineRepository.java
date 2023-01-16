package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Domaine;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends PagingAndSortingRepository<Domaine,Long> {
    Domaine findByNomDomaine(String nomDomaine);
    Domaine findById(String id);
    Boolean existsByNomDomaineAndEtudiantIsTrue(String nomDomaine);
}
