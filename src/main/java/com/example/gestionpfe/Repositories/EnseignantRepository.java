package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnseignantRepository extends CrudRepository<Enseignant,Long> {
    Enseignant findByEmail(String email);
    Enseignant findByIdEnseignant(String idEnseignant);
    Enseignant findByEmailVerificationToken(String emailVerificationToken);
}

