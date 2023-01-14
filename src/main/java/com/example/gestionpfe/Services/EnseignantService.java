package com.example.gestionpfe.Services;

import com.example.gestionpfe.Controllers.Dto.EnseignantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EnseignantService extends UserDetailsService {
    EnseignantDto addEnseignant(EnseignantDto enseignantDto);
    EnseignantDto getEnseignant(String email);
    EnseignantDto getEnseignantByIdEnseignant(String id);
    EnseignantDto updateEnseignant(String id,EnseignantDto enseignantDto);
    void deleteEnseignant(String id);


}
