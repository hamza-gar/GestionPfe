package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.InvitationDto;
import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.InvitationRepository;
import com.example.gestionpfe.Repositories.JuryRepository;
import com.example.gestionpfe.Repositories.SoutenanceRepository;
import com.example.gestionpfe.Requests.InvitationRequest;
import com.example.gestionpfe.Services.JuryService;
import com.example.gestionpfe.Services.SoutenanceService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import jdk.jshell.execution.Util;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuryServiceImpl implements JuryService {
    Logger logger = org.slf4j.LoggerFactory.getLogger(JuryServiceImpl.class);

    @Autowired
    Utils util;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    SoutenanceRepository soutenanceRepository;

    @Autowired
    JuryRepository juryRepository;

    @Autowired
    EmailSender mailSender;


    @Override
    public Boolean reponseInvitation(InvitationDto invitationDto) {
        Invitation invitation = invitationRepository.findByIdInvitation(invitationDto.getIdInvitation());
        if (invitation == null) {
            logger.warn("Invitation not found");
            throw new RuntimeException("Invitation not found");
        }

        if (invitationDto.getAccepted()) {
            Jury jury = new Jury();
            jury.setEnseignant(enseignantRepository.findByEmail(invitation.getEmailInvite()));
            jury.setSoutenance(soutenanceRepository.findByIdSoutenance(invitation.getIdSoutenance()));
            jury.setTypeJury(invitation.getRole());
            jury.setIdJury(util.generateUserId(32));
            juryRepository.save(jury);
            logger.info("Jury accepted invitation,Jury added to soutenance : " + invitation.getIdSoutenance());
            mailSender.InvitationAccepted(soutenanceRepository.findByIdSoutenance(invitation.getIdSoutenance()).getSujet().getEncadrant().getEmail(), invitation.getEmailInvite());
        } else {
            logger.info("Jury declined invitation");
            mailSender.InvitationRefused(soutenanceRepository.findByIdSoutenance(invitation.getIdSoutenance()).getSujet().getEncadrant().getEmail(), invitation.getEmailInvite());

        }

        invitationRepository.delete(invitation);
        logger.info("Invitation deleted");

        return invitationDto.getAccepted();
    }

    @Override
    public List<SoutenanceDto> getSoutenanceByJury(String mailJury, int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
        Page<Soutenance> soutenancesPage = soutenanceRepository.findAllByJuryEmail(mailJury, pageableRequest);
        for (Soutenance soutenance : soutenancesPage.getContent()) {
            SoutenanceDto soutenanceDto = new SoutenanceDto();
            soutenanceDto.setIdSoutenance(soutenance.getIdSoutenance());
            soutenanceDto.setDateSoutenance(soutenance.getDateSoutenance());
            soutenanceDto.setJurys(soutenance.getJurys());
            soutenanceDto.setId(soutenance.getId());
            soutenanceDto.setSujet(soutenance.getSujet());
            soutenanceDtos.add(soutenanceDto);
        }
        logger.info("Soutenances found for jury : " + mailJury);
        return soutenanceDtos;
    }
}
