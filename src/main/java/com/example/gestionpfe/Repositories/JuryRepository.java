package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Jury;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuryRepository extends PagingAndSortingRepository<Jury,Long> {
    Jury findByIdJury(String IdJury);
    List<Jury> findAllByTypeJury(String typeJury);
    Page<Jury> findAllBySoutenance_Sujet_IdSujet(String idSujet, Pageable pageable);
}
