package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.Authority;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String name);

}
