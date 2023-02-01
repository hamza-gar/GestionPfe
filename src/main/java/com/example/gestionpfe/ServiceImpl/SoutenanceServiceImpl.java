package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.Repositories.*;
import com.example.gestionpfe.Services.SoutenanceService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SoutenanceServiceImpl implements SoutenanceService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(SoutenanceServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SoutenanceRepository soutenanceRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    SujetRepository sujetRepository;

    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public SoutenanceDto addSoutenance(String username, SoutenanceDto soutenanceDto, String idSujet) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }

        Sujet sujet = sujetRepository.findByIdSujet(idSujet);
        if (sujet == null) {
            logger.warn("Sujet not found");
            throw new RuntimeException("Sujet not found");
        }

        if (!sujet.getEncadrant().getIdEnseignant().equals(enseignant.getIdEnseignant())) {
            logger.warn("Enseignant is not the owner of the sujet");
            throw new RuntimeException("Enseignant is not the owner of the sujet");
        }

        if (!sujet.getLocked()) {
            logger.warn("Sujet is not locked");
            throw new RuntimeException("Sujet is not locked");
        }

        if (!sujet.getDone()) {
            logger.warn("Sujet is not done");
            throw new RuntimeException("Sujet is not done");
        }

        if (sujet.getEquipe().size() != 1) {
            logger.warn("Sujet has more than one team");
            throw new RuntimeException("Sujet has more than one team");
        }

        if (sujet.getEquipe().get(0).getDriveLink() == null || sujet.getEquipe().get(0).getDriveLink().isEmpty()) {
            logger.warn("Sujet has no drive link");
            throw new RuntimeException("Sujet has no drive link");
        }

        if (sujet.getSoutenance() != null) {
            logger.warn("Sujet has already a soutenance");
            throw new RuntimeException("Sujet has already a soutenance");
        }

        if (soutenanceDto.getDateSoutenance() == null) {
            logger.warn("Soutenance date is null");
            throw new RuntimeException("Soutenance date is null");
        }

        if (soutenanceDto.getDateSoutenance().before(new Date())) {
            logger.warn("Soutenance date is before today");
            throw new RuntimeException("Soutenance date is before today");
        }

        Soutenance soutenance = new Soutenance();
        soutenance.setIdSoutenance(util.generateUserId(32));
        soutenance.setSujet(sujet);
        soutenance.setEnded(false);
        soutenance.setDateSoutenance(soutenanceDto.getDateSoutenance());

        Soutenance soutenanceSaved = soutenanceRepository.save(soutenance);

        logger.info("Soutenance saved successfully");

        return modelMapper.map(soutenanceSaved, SoutenanceDto.class);
    }

    @Override
    public SoutenanceDto getSoutenanceByIdSoutenance(String username, String id) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        SoutenanceDto soutenanceDto = modelMapper.map(soutenance, SoutenanceDto.class);
        return soutenanceDto;
    }

    @Override
    public Boolean inviteJurys(String username, String roleJury, String emailJury, SoutenanceDto soutenanceDto) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(soutenanceDto.getIdSoutenance());
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        logger.info("Soutenance found successfully");
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        logger.info("Enseignant found successfully : " + username);
        Enseignant enseignantJury = enseignantRepository.findByEmail(emailJury);
        if (enseignantJury == null) {
            logger.warn("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        logger.info("Jury found successfully : " + emailJury);
        if (!username.equals(soutenance.getSujet().getEncadrant().getEmail())) {
            logger.warn("Enseignant is not the owner of the sujet");
            throw new RuntimeException("Enseignant is not the owner of the sujet");
        }

        if (soutenance.getJurys().size() == 3) {
            logger.warn("You can't invite anymore jury for this soutenance");
            throw new RuntimeException("You can't invite anymore jury for this soutenance");
        }

        if (username.equals(emailJury)) {
            logger.warn("Enseignant can't invite himself");
            throw new RuntimeException("Enseignant can't invite himself");
        }
        for (Jury jury : soutenance.getJurys()) {
            if (jury.getEnseignant().getEmail().equals(emailJury)) {
                logger.warn("Enseignant is already a jury for this soutenance");
                throw new RuntimeException("Enseignant is already a jury for this soutenance");
            }
        }
        if (invitationRepository.findByEmailInvite(emailJury) != null) {
            logger.warn("Enseignant is already invited");
            throw new RuntimeException("Enseignant is already invited");
        }
        if (soutenance.getDateSoutenance() == null) {
            logger.warn("Soutenance has no date, you must specify a date before inviting a jury member");
            throw new RuntimeException("Soutenance has no date, you must specify a date before inviting a jury member");
        }

        Invitation invitation = new Invitation();
        invitation.setIdInvitation(util.generateUserId(32));
        invitation.setIdSoutenance(soutenance.getIdSoutenance());
        invitation.setEmailInvite(emailJury);
        invitation.setRole(roleJury);
        invitationRepository.save(invitation);
        logger.info("Invitation saved successfully");

        emailSender.sendInvitationJury(emailJury, invitation.getIdInvitation(), roleJury, soutenance.getDateSoutenance().toString(), soutenance.getSujet().getNomSujet());

        return true;
    }

    @Override
    public SoutenanceDto updateDateSoutenance(String username, SoutenanceDto soutenanceDto) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        logger.info("Enseignant found successfully : " + username);
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(soutenanceDto.getIdSoutenance());
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        logger.info("Soutenance found successfully");
        if (!username.equals(soutenance.getSujet().getEncadrant().getEmail())) {
            logger.warn("Enseignant is not the owner of the sujet");
            throw new RuntimeException("Enseignant is not the owner of the sujet");
        }
        if (soutenance.getEnded()) {
            logger.warn("Soutenance has already ended");
            throw new RuntimeException("Soutenance has already ended");
        }
        if (soutenanceDto.getDateSoutenance() == null) {
            logger.warn("Date is null");
            throw new RuntimeException("Date is null");
        }
        if (soutenanceDto.getDateSoutenance().before(new Date())) {
            logger.warn("Date is before today");
            throw new RuntimeException("Date is before today");
        }
        soutenance.setDateSoutenance(soutenanceDto.getDateSoutenance());
        soutenanceRepository.save(soutenance);
        logger.info("Soutenance's date updated successfully");

        return modelMapper.map(soutenance, SoutenanceDto.class);
    }

    @Override
    public SoutenanceDto updateSoutenance(String id, SoutenanceDto soutenanceDto) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }

        soutenance.setDateSoutenance(soutenanceDto.getDateSoutenance());
        Soutenance soutenanceSaved = soutenanceRepository.save(soutenance);
        logger.info("Soutenance updated successfully");
        SoutenanceDto soutenanceDtoUpdated = modelMapper.map(soutenanceSaved, SoutenanceDto.class);
        return soutenanceDtoUpdated;
    }

    @Override
    public void deleteSoutenance(String id) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        soutenanceRepository.delete(soutenance);
        logger.info("Soutenance deleted successfully");
    }

    @Override
    public List<SoutenanceDto> getAllSoutenance(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        List<SoutenanceDto> soutenanceDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Soutenance> soutenancePage = soutenanceRepository.findAll(pageableRequest);

        List<Soutenance> soutenances = soutenancePage.getContent();
        for (Soutenance soutenance : soutenances) {
            SoutenanceDto soutenanceDto1 = modelMapper.map(soutenance, SoutenanceDto.class);
            soutenanceDto.add(soutenanceDto1);
        }
        return soutenanceDto;
    }
}
