package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.InvitationDto;
import com.example.gestionpfe.Dto.JuryDto;
import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Soutenance;
import com.example.gestionpfe.Requests.InvitationRequest;

import java.util.List;

public interface JuryService {
    JuryDto addJury(Enseignant enseignant, Soutenance soutenance, String role);
    Boolean reponseInvitation(InvitationDto invitationDto);
    List<SoutenanceDto> getSoutenanceByJury(String mailJury,int page, int limit);
    List<EnseignantDto> getEnseignantsToInvite(String username,int page, int limit);
    List<JuryDto> getJuryBySujet(String idSujet,int page, int limit);
}
