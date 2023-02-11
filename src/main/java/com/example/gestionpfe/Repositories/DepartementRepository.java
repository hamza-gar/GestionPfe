package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Departement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends PagingAndSortingRepository<Departement,Long> {
    Departement findByIdDepartement(String idDepartement);
    Departement findByNomDepartement(String nomDepartement);
    List<Departement> findAllByEtablissement_IdEtablissement(String idEtablissement);
    Departement findById(String id);
}
