package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant,Long> {
    Etudiant findByEmail(String email);
    Etudiant findByIdEtudiant(String idEtudiant);
}
