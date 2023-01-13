package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.EnseignantDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface EnseignantService extends UserDetailsService {
    EnseignantDto addEnseignant(EnseignantDto enseignantDto);
    EnseignantDto getEnseignant(String email);
    EnseignantDto getEnseignantByIdEnseignant(String id);
    EnseignantDto updateEnseignant(String id,EnseignantDto enseignantDto);
    void deleteEnseignant(String id);


}
