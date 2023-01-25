package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Entities.Filiere;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends PagingAndSortingRepository<Filiere,Long> {
    Filiere findByNomFiliere(String nomFiliere);
    Filiere findByIdFiliere(String idFiliere);

}

