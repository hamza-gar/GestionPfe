package com.example.gestionpfe.Repositories;


import com.example.gestionpfe.Entities.Administrateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Administrateur,Long> {
    Administrateur findByEmail(String email);
    Administrateur findByIdAdmin(String idAdmin);
}
