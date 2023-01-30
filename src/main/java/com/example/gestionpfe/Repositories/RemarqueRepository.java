package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Remarque;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarqueRepository extends PagingAndSortingRepository<Remarque,Long> {
Remarque findByIdRemarque(String idRemarque);
}
