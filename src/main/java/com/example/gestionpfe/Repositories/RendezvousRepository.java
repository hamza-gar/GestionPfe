package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Rendezvous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RendezvousRepository extends PagingAndSortingRepository<Rendezvous, Long> {
    Rendezvous findByIdRendezvous(String idRendezvous);

    Rendezvous findByDateRendezvous(Date dateRendezvous);

    Rendezvous findByEquipe_IdEquipe(String idEquipe);

    Page<Rendezvous> findAllByEncadrant_Email(String email, Pageable pageable);

    Float countAllByEncadrant_Email(String username);
}
