package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.EtudiantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EtudiantService extends UserDetailsService {
    EtudiantDto addEtudiant(EtudiantDto etudiantDto);
    EtudiantDto getEtudiant(String email);
    EtudiantDto getEtudiantByIdEtudiant(String id);
    EtudiantDto updateEtudiant(String id,EtudiantDto etudiantdto);
    EtudiantDto verifyEtudiant(String token);
    EtudiantDto resendVerification(String etudiantId);
    void deleteEtudiant(String id);
}
