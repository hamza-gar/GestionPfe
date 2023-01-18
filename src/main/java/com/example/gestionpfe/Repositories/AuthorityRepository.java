package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(String name);
}
