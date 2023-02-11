package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Enseignant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnseignantRepository extends PagingAndSortingRepository<Enseignant,Long> {
    Enseignant findByEmail(String email);
    Enseignant findByIdEnseignant(String idEnseignant);
    Enseignant findByEmailVerificationToken(String emailVerificationToken);
    Page<Enseignant> findAllByDepartement_Etablissement_IdEtablissementAndEmailIsNot(String idEtablissement, String email, Pageable pageable);
}

