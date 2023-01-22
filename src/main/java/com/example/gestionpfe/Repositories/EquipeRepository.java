package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Etudiant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends PagingAndSortingRepository<Equipe, Long> {
    Equipe findByIdEquipe(String idEquipe);
    List<Equipe> findByEtudiant(Etudiant etudiant);

}
