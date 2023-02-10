package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.RendezvousDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Rendezvous;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.RendezvousRepository;
import com.example.gestionpfe.Services.RendezvousService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RendezvousServiceImpl implements RendezvousService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(RendezvousServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    RendezvousRepository rendezvousRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EmailSender mailSender;

    @Override
    public RendezvousDto addRendezvous(String username) {
        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("Etudiant not found");
            throw new RuntimeException("Etudiant not found");
        }
        List<Equipe> equipes = etudiant.getEquipe();
        if (equipes.size() == 0) {
            logger.info("This etudiant doesnt belong to any team.");
            throw new RuntimeException("This etudiant doesnt belong to any team.");
        }
        if (equipes.size() != 1) {
            logger.info("This etudiant belongs to more than one team.");
            throw new RuntimeException("This etudiant belongs to more than one team.");
        }

        Equipe equipe = equipes.get(0);
        if (equipe.getSujet().getLocked() == false) {
            logger.info("This team doesnt have a subject yet.");
            throw new RuntimeException("This team doesnt have a subject yet.");
        }
        if (equipe.getRendezvous() != null) {
            logger.info("This team already has a rendezvous.");
            throw new RuntimeException("This team already has a rendezvous.");
        }

        Rendezvous rendezvousEntity = new Rendezvous();
        rendezvousEntity.setIdRendezvous(util.generateUserId(32));
        rendezvousEntity.setVote(0);
        rendezvousEntity.setEquipe(equipe);
        rendezvousEntity.setFixed(false);
        rendezvousEntity.setFlags("");
        logger.info("Equipe added successfully.");
        rendezvousEntity.setEncadrant(equipe.getSujet().getEncadrant());
        logger.info("Encadrant added successfully." + equipe.getSujet().getEncadrant().getIdEnseignant() + " " + equipe.getSujet().getEncadrant().getId());
        rendezvousEntity.setDateRendezvous(null);


        Rendezvous newRendezvous = rendezvousRepository.save(rendezvousEntity);

        mailSender.DemandeRendezVous(equipe.getSujet().getEncadrant().getEmail(), equipe.getSujet().getNomSujet());
        logger.info("Rendezvous added successfully.");

        return modelMapper.map(newRendezvous, RendezvousDto.class);
    }

    @Override
    public RendezvousDto getRendezvousByDate(Date dateRendezvous) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByDateRendezvous(dateRendezvous);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");
        RendezvousDto rendezvousDto = new RendezvousDto();
        rendezvousDto = modelMapper.map(rendezvousEntity, RendezvousDto.class);
        logger.info("Rendezvous found successfully");
        return rendezvousDto;
    }

    @Override
    public RendezvousDto getRendezvousByIdRendezvous(String username, String id) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(id);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");

        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant != null) {
            if (etudiant.getEquipe().get(0).getSujet().getNomSujet().equals(rendezvousEntity.getEquipe().getSujet().getNomSujet())) {
                logger.info("Rendezvous found successfully");
                return modelMapper.map(rendezvousEntity, RendezvousDto.class);
            }
        } else {
            if (rendezvousEntity.getEncadrant().getEmail().equals(username)) {
                logger.info("Rendezvous found successfully");
                return modelMapper.map(rendezvousEntity, RendezvousDto.class);
            }
        }
        logger.info("Not authorized !!!");
        throw new RuntimeException("Not authorized !!!");
    }

    @Override
    public RendezvousDto fixRendezVous(String username, RendezvousDto rendezvousDto) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(rendezvousDto.getIdRendezvous());
        if (rendezvousEntity == null) {
            logger.info("Rendezvous not found");
            throw new RuntimeException("Rendezvous not found !!!");
        }
        if (rendezvousEntity.getEncadrant().getIdEnseignant() != enseignant.getIdEnseignant()) {
            logger.info("You are not the encadrant of this team");
            throw new RuntimeException("You are not the encadrant of this team");
        }
        if (rendezvousEntity.getDateRendezvous() != null) {
            logger.info("This rendezvous's Date is already fixed");
            throw new RuntimeException("This rendezvous's Date is already fixed");
        }
        if (rendezvousDto.getDateRendezvous() == null) {
            logger.info("You must specify a date");
            throw new RuntimeException("You must specify a date");
        }
        if (rendezvousDto.getDateRendezvous().before(new Date())) {
            logger.info("You must specify a date in the future");
            throw new RuntimeException("You must specify a date in the future");
        }
        rendezvousEntity.setDateRendezvous(rendezvousDto.getDateRendezvous());

        Rendezvous rendezvous = rendezvousRepository.save(rendezvousEntity);
        for (Etudiant et : rendezvous.getEquipe().getEtudiant()) {
            String etudiantMail = et.getEmail();
            mailSender.RendezVousFixed(etudiantMail,username,rendezvous.getDateRendezvous());
        }
        //mailSender.RendezVousFixed("abdellah.samourail@gmail.com", username, rendezvous.getDateRendezvous());

        logger.info("Rendezvous updated successfully");

        return modelMapper.map(rendezvous, RendezvousDto.class);
    }

    @Override
    public RendezvousDto voteRendezvous(String username, RendezvousDto rendezvousDto) {
        if (rendezvousDto.getIdRendezvous() == null) {
            logger.info("You must specify a rendezvous");
            throw new RuntimeException("You must specify a rendezvous");
        }
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(rendezvousDto.getIdRendezvous());
        if (rendezvousEntity == null) {
            logger.info("Rendezvous not found");
            throw new RuntimeException("Rendezvous not found !!!");
        }
        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("Etudiant not found");
            throw new RuntimeException("Etudiant not found");
        }
        if (rendezvousEntity.getEquipe().getIdEquipe() != etudiant.getEquipe().get(0).getIdEquipe()) {
            logger.info("You are not in this team");
            throw new RuntimeException("You are not in this team");
        }
        if (rendezvousEntity.getDateRendezvous() == null) {
            logger.info("You cant vote for a rendezvous without a date");
            throw new RuntimeException("You cant vote for a rendezvous without a date");
        }
        if (Arrays.stream(rendezvousEntity.getFlags().split(",")).anyMatch(etudiant.getIdEtudiant()::equals)) {
            logger.info("You already voted for this rendezvous");
            throw new RuntimeException("You already voted for this rendezvous");
        }
        if (rendezvousDto.getVote() == 0) {
            logger.info("You must specify a vote");
            throw new RuntimeException("You must specify a vote");
        }
        if (rendezvousEntity.getDateRendezvous().before(new Date())) {
            logger.info("You cant vote for a rendezvous in the past");
            throw new RuntimeException("You cant vote for a rendezvous in the past");
        }
        rendezvousEntity.setVote(rendezvousEntity.getVote() + rendezvousDto.getVote());
        if (rendezvousEntity.getFlags().equals("")) {
            rendezvousEntity.setFlags(etudiant.getIdEtudiant());
        } else {
            rendezvousEntity.setFlags(rendezvousEntity.getFlags() + "," + etudiant.getIdEtudiant());
        }
        if (rendezvousEntity.getFlags().split(",").length == rendezvousEntity.getEquipe().getTailleEquipe()) {
            if (rendezvousEntity.getVote() > 0) {
                for (Etudiant et : rendezvousEntity.getEquipe().getEtudiant()) {
                    mailSender.RendezVousPris(et.getEmail(), rendezvousEntity.getDateRendezvous());
                }
                mailSender.RendezVousPris(rendezvousEntity.getEncadrant().getEmail(), rendezvousEntity.getDateRendezvous());

                rendezvousEntity.setFixed(true);
            } else {
                mailSender.RendezVousNonPrisEns(rendezvousEntity.getEncadrant().getEmail(), rendezvousEntity.getEquipe().getSujet().getNomSujet());
                for (Etudiant et : rendezvousEntity.getEquipe().getEtudiant()) {
                    mailSender.RendezVousNonPrisEt(et.getEmail());
                }
                rendezvousEntity.setVote(0);
                rendezvousEntity.setFlags("");
                rendezvousEntity.setDateRendezvous(null);
                rendezvousEntity.setFixed(false);
            }
        }
        Rendezvous rendezvous = rendezvousRepository.save(rendezvousEntity);
        return modelMapper.map(rendezvous, RendezvousDto.class);
    }

    @Override
    public void deleteRendezvous(String id) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(id);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");
        rendezvousRepository.delete(rendezvousEntity);
        logger.info("Rendezvous deleted successfully");
    }

    @Override
    public List<RendezvousDto> getAllRendezvous(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("Not authorized !!!");
            throw new RuntimeException("Not authorized !!!");
        }

        List<RendezvousDto> rendezvousDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Rendezvous> rendezvousPage = rendezvousRepository.findAllByEncadrant_Email(username, pageableRequest);
        for (Rendezvous rendezvousEntity : rendezvousPage.getContent()) {
            RendezvousDto rendezvousDto = modelMapper.map(rendezvousEntity, RendezvousDto.class);
            rendezvousDto.setIdEquipe(rendezvousEntity.getEquipe().getIdEquipe());
            rendezvousDto.setNomSujet(rendezvousEntity.getEquipe().getSujet().getNomSujet());
            rendezvousDtos.add(rendezvousDto);
        }

        logger.info("all Rendezvous found successfully");
        return rendezvousDtos;
    }

}
