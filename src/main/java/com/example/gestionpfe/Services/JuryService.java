package com.example.gestionpfe.Services;

import com.example.gestionpfe.Requests.InvitationRequest;

public interface JuryService {
    Boolean reponseInvitation(String username, InvitationRequest invitationRequest);
}
