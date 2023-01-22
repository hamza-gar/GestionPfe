package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends PagingAndSortingRepository<Etudiant,Long> {
    Etudiant findByEmail(String email);
    Etudiant findByIdEtudiant(String idEtudiant);
    Etudiant findByEmailVerificationToken(String emailVerificationToken);
}
