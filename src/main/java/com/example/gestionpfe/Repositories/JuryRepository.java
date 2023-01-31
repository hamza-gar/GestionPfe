package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Jury;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuryRepository extends PagingAndSortingRepository<Jury,Long> {
    Jury findByIdJury(String IdJury);
    List<Jury> findAllByTypeJury(String typeJury);
}
