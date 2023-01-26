package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Rendezvous;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RendezvousRepository extends PagingAndSortingRepository<Rendezvous,Long> {
Rendezvous findByIdRendezvous(String idRendezvous);
// date java or date sql ???
Rendezvous findByDateRendezvous(Date dateRendezvous);
}