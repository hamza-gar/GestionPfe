package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    Etablissement findByNomEtablissement(String nomEtablissement);
    Etablissement findByIdEtablissement(String idEtablissement);
}
