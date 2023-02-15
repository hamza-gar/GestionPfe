package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Domaine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends PagingAndSortingRepository<Domaine,Long> {
    Domaine findByNomDomaine(String nomDomaine);
    Domaine findById(String id);
    Boolean existsByNomDomaineAndEtudiantIsTrue(String nomDomaine);
    Boolean existsByNomDomaine(String nomDomaine);
    @Query("SELECT d FROM domaines d WHERE d.id = :id")
    Domaine getDomaineById(@Param("id") long id);
}
