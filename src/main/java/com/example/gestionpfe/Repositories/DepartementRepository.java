package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Departement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends PagingAndSortingRepository<Departement,Long> {
    Departement findByIdDepartement(String idDepartement);
    Departement findByNomDepartement(String nomDepartement);
    Departement findById(String id);
}
