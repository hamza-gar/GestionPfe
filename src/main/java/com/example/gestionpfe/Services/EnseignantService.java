package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EnseignantService extends UserDetailsService {
    EnseignantDto addEnseignant(EnseignantDto enseignantDto);
    EnseignantDto getEnseignant(String email);
    EnseignantDto getEnseignantByIdEnseignant(String id);
    EnseignantDto updateEnseignant(String id,EnseignantDto enseignantDto);
    EnseignantDto verifyEnseignant(String token);
    EnseignantDto resendVerification(String enseignantId);
    void deleteEnseignant(String id);


}
