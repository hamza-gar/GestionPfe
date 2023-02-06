package com.example.gestionpfe.Repositories;

import com.example.gestionpfe.Entities.PasswordHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordHelperRepository extends JpaRepository<PasswordHelper, Long> {
    PasswordHelper findByIdPasswordHelper(String idPasswordHelper);
    PasswordHelper findByEmail(String email);
    PasswordHelper findByKeyCodeAndEmail(String key, String email);
}
