package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Soutenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SoutenanceRepository extends PagingAndSortingRepository<Soutenance,Long> {
    Soutenance findByIdSoutenance(String idSoutenance);
    Soutenance findByDateSoutenance(Date dateSoutenance);

    @Query("SELECT s FROM soutenances s JOIN s.jurys jury WHERE jury.enseignant.email = :mailJury")
    Page<Soutenance> findAllByJuryEmail(@Param("mailJury") String mailJury, Pageable pageable);


}
