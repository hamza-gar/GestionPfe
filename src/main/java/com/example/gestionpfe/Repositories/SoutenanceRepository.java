package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Soutenance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SoutenanceRepository extends PagingAndSortingRepository<Soutenance,Long> {
    Soutenance findByIdSoutenance(String idSoutenance);
    Soutenance findByDateSoutenance(Date dateSoutenance);
}
