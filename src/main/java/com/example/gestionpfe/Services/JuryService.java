package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.InvitationDto;
import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Requests.InvitationRequest;

import java.util.List;

public interface JuryService {
    Boolean reponseInvitation(InvitationDto invitationDto);
    List<SoutenanceDto> getSoutenanceByJury(String mailJury,int page, int limit);
}
