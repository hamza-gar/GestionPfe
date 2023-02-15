package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Remarque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarqueRepository extends PagingAndSortingRepository<Remarque,Long> {
    Remarque findByIdRemarque(String idRemarque);

    Page<Remarque> findAllByEtudiant_IdEtudiant(String idEtudiant, Pageable pageable);

    Long countAllByEtudiant_IdEtudiant(String idEtudiant);
}
