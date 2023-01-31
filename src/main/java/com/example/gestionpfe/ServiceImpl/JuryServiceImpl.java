package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.InvitationRepository;
import com.example.gestionpfe.Requests.InvitationRequest;
import com.example.gestionpfe.Services.JuryService;
import com.example.gestionpfe.Services.SoutenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuryServiceImpl implements JuryService {

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    SoutenanceService soutenanceService;

    @Autowired
    InvitationRepository invitationRepository;


    @Override
    public Boolean reponseInvitation(String username, InvitationRequest invitationRequest) {

        return null;
    }
}
