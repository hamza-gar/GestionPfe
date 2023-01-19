package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.EnseignantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EnseignantService extends UserDetailsService {
    EnseignantDto addEnseignant(EnseignantDto enseignantDto);
    EnseignantDto getEnseignant(String email);
    EnseignantDto getEnseignantByIdEnseignant(String id);
    EnseignantDto updateEnseignant(String id,EnseignantDto enseignantDto);
    EnseignantDto verifyEnseignant(String token);
    EnseignantDto resendVerification(String enseignantId);
    List<EnseignantDto> getAllEnseignants(int page, int limit);
    void deleteEnseignant(String id);


}
