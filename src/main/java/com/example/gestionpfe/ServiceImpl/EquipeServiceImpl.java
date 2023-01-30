package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EquipeDto;
import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.*;

import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EquipeRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.SujetRepository;
import com.example.gestionpfe.Services.EquipeService;
import com.example.gestionpfe.Services.EtudiantService;
import com.example.gestionpfe.Services.SujetService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipeServiceImpl implements EquipeService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(EquipeServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private RestTemplate restTemplate;

    @Autowired
    Utils util;

    @Autowired
    SujetService sujetService;

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    SujetRepository sujetRepository;

    @Autowired
    EnseignantRepository enseignantRepository;


    @Override
    public EquipeDto addEquipe(String username, String sujetId, EquipeDto equipeDto) {

        /* TODO: check if the student is already affiliated to an already existing team */

        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("etudiant not found");
            throw new RuntimeException("etudiant not found !!!");
        }
        equipeDto.getEtudiant().add(etudiant);
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);

        equipeDto.setEtudiant(etudiants);


        SujetDto sujet = sujetService.getSujetById(sujetId);
        if (sujet == null) {
            logger.info("sujet not found");
            throw new RuntimeException("sujet not found !!!");
        }
        if (sujet.getLocked()) {
            logger.info("This subject is locked.");
            throw new RuntimeException("This subject is locked.");
        }

        if (etudiantService.etudiantAlreadyInSujet(equipeDto.getEtudiant().get(0).getIdEtudiant(), sujetId)) {
            logger.info("This Etudiant is already existing in a team for this subject.");
            throw new RuntimeException("This Etudiant is already existing in a team for this subject.");
        }

        if (etudiantService.etudiantIn3Sujets(equipeDto.getEtudiant().get(0).getIdEtudiant(), sujet.getEncadrant().getIdEnseignant())) {
            logger.info("This Etudiant is already existing in 3 teams.");
            throw new RuntimeException("This Etudiant is already existing in 3 teams.");
        }
        Sujet sujetEntity = new Sujet();
        sujetEntity = modelMapper.map(sujet, Sujet.class);

        logger.info("Sujet :" + sujetEntity.getIdSujet());

        Equipe equipeEntity = new Equipe();
        equipeEntity = modelMapper.map(equipeDto, Equipe.class);

        equipeEntity.setIdEquipe(util.generateUserId(32));
        logger.info("Sujet :" + sujetEntity.getIdSujet());
        equipeEntity.setSujet(sujetEntity);
        equipeEntity.setTailleEquipe(sujetEntity.getTailleEquipe());
        logger.info("equipe :" + equipeEntity.getIdEquipe());
        Equipe newEquipe = equipeRepository.save(equipeEntity);

        EquipeDto newEquipeDto = new EquipeDto();
        newEquipeDto = modelMapper.map(newEquipe, EquipeDto.class);
        logger.info("equipe found successfully");

        return newEquipeDto;
    }


    @Override
    public EquipeDto getEquipeByIdEquipe(String idEquipe) {

        Equipe equipeEntity = equipeRepository.findByIdEquipe(idEquipe);

        if (equipeEntity == null) {
            logger.info("equipe not found");
            throw new UsernameNotFoundException(idEquipe);
        }
        logger.info("equipe found successfully");

        EquipeDto equipeDto = new EquipeDto();
        equipeDto = modelMapper.map(equipeEntity, EquipeDto.class);
        return equipeDto;

    }

    @Override
    public EquipeDto getEquipeById(String id) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(id);

        if (EquipeEntity == null) throw new UsernameNotFoundException(id);

        EquipeDto equipeDto = new EquipeDto();
        equipeDto = modelMapper.map(EquipeEntity, EquipeDto.class);
        logger.info("domaine found successfully");
        return equipeDto;
    }

    @Override
    public EquipeDto updateEquipe(EquipeDto equipeDto) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(equipeDto.getIdEquipe());

        if (EquipeEntity == null) {
            logger.info("equipe not found");
            throw new UsernameNotFoundException(equipeDto.getIdEquipe());
        }

        EquipeEntity.setTailleEquipe(equipeDto.getTailleEquipe());
        EquipeEntity.setEtudiant(equipeDto.getEtudiant());

        Equipe equipeeUpdated = equipeRepository.save(EquipeEntity);

        EquipeDto newequipeDto = new EquipeDto();
        newequipeDto = modelMapper.map(equipeeUpdated, EquipeDto.class);
        logger.info("Equipe updated successfully.");

        return newequipeDto;
    }

    @Override
    public EquipeDto removeEtudiant(String idEquipe, String idEtudiant) {
        Equipe equipeEntity = equipeRepository.findByIdEquipe(idEquipe);
        if (equipeEntity == null) {
            logger.info("equipe not found");
            throw new RuntimeException("equipe with id : " + idEquipe + " not found !!!");
        }
        logger.info("equipe found successfully");
        idEtudiant = idEtudiant.contains("@") ? etudiantRepository.findByEmail(idEtudiant).getIdEtudiant() : idEtudiant;
        Etudiant etudiant = etudiantRepository.findByIdEtudiant(idEtudiant);
        if (etudiant == null) {
            logger.info("etudiant not found");
            throw new RuntimeException("etudiant with id : " + idEtudiant + " not found !!!");
        }
        List<Etudiant> etudiants = new ArrayList<>();
        for (Etudiant etu : equipeEntity.getEtudiant()) {
            if (!etu.getIdEtudiant().equals(idEtudiant)) {
                etudiants.add(etu);
            }
        }
        if (etudiants.size() == 0) {
            equipeRepository.delete(equipeEntity);
            return null;
        }
        equipeEntity.setEtudiant(etudiants);
        Equipe equipeSaved = equipeRepository.save(equipeEntity);
        return modelMapper.map(equipeSaved, EquipeDto.class);
    }

    @Override
    public EquipeDto joinEquipe(String username, EquipeDto equipeDto) {
        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("etudiant not found");
            throw new RuntimeException("etudiant not found !!!");
        }
        logger.info("etudiant found successfully");
        Equipe equipeEntity = equipeRepository.findByIdEquipe(equipeDto.getIdEquipe());
        if (equipeEntity == null) {
            logger.info("equipe not found");
            throw new RuntimeException("equipe with id : " + equipeDto.getIdEquipe() + " not found !!!");
        }
        logger.info("equipe found successfully");
        if (equipeEntity.getEtudiant().size() == equipeEntity.getTailleEquipe()) {
            logger.info("This team is full.");
            throw new RuntimeException("This team is full.");
        }
        logger.info("l'equipe n'est pas pleine.");
        if (etudiantService.etudiantAlreadyInSujet(etudiant.getIdEtudiant(), equipeEntity.getSujet().getIdSujet())) {
            logger.info("This Etudiant is already existing in this team.");
            throw new RuntimeException("This Etudiant is already existing in this team.");
        }
        logger.info("l'etudiant n'est pas deja dans l'equipe.");
        if (etudiantService.etudiantIn3Sujets(etudiant.getIdEtudiant(), equipeEntity.getSujet().getEncadrant().getIdEnseignant())) {
            logger.info("This Etudiant is already existing in 3 different 'Sujets' of the same 'Encadrant'.");
            throw new RuntimeException("This Etudiant is already existing in 3 different 'Sujets' of the same 'Encadrant'.");
        }
        logger.info("l'etudiant n'est pas deja dans 3 equipes pour ce meme encadrant.");

        if (equipeEntity.getIsPrivate()) {
            if(equipeDto.getCryptedPassword() == null){
                logger.info("equipe is private, password is required");
                throw new RuntimeException("equipe is private, password is required");
            }
            if (!equipeDto.getCryptedPassword().equals(equipeEntity.getCryptedPassword())) {
                logger.info("password is incorrect");
                throw new RuntimeException("password is incorrect");
            }
        }

        List<Etudiant> etudiants = equipeEntity.getEtudiant();
        for (Etudiant etu : etudiants) {
            if (etu.getIdEtudiant().equals(etudiant.getIdEtudiant())) {
                logger.info("etudiant already in equipe");
                throw new RuntimeException("etudiant already in equipe");
            }
        }

        etudiants.add(etudiant);
        equipeEntity.setEtudiant(etudiants);

        Equipe equipeeUpdated = equipeRepository.save(equipeEntity);

        logger.info("Equipe updated successfully.");

        return modelMapper.map(equipeeUpdated, EquipeDto.class);
    }

    @Override
    public void deleteEquipe(String id) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(id);

        if (EquipeEntity == null) throw new UsernameNotFoundException(id);

        equipeRepository.delete(EquipeEntity);
        logger.info("equipe deleted successfully.");
    }

    @Override
    public List<EquipeDto> getAllEquipes(String username,int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("enseignant not found");
            throw new RuntimeException("enseignant not found !!!");
        }

        List<EquipeDto> equipeDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Equipe> equipePage = equipeRepository.findAll(pageableRequest);
        List<Equipe> equipeList = equipePage.getContent();
        for (Equipe equipe : equipeList) {

            if(equipe.getSujet().getEncadrant().getIdEnseignant().equals(enseignant.getIdEnseignant())){
                EquipeDto equipeDto1 = new EquipeDto();
                equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
                equipeDto.add(equipeDto1);
            }
        }
        return equipeDto;
    }

    @Override
    public List<EquipeDto> getGroupesOfSujets(String username,String idSujet, int page, int limit) {
        Boolean isEtudiant;
        if (enseignantRepository.findByEmail(username) == null) {
            if (etudiantRepository.findByEmail(username) == null) {
                logger.info("No user found !!!");
                throw new RuntimeException("No user found !!!");
            }else {
                logger.info("etudiant found");
                isEtudiant = true;
            }
        }else {
            logger.info("enseignant found");
            isEtudiant = false;
        }

        Sujet sujet = sujetRepository.findByIdSujet(idSujet);
        if (sujet == null) {
            logger.info("sujet not found");
            throw new RuntimeException("sujet not found !!!");
        }
        List<EquipeDto> equipeDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Equipe> equipePage = equipeRepository.findAll(pageableRequest);
        List<Equipe> equipeList = equipePage.getContent();
        for (Equipe equipe : equipeList) {
            if(equipe.getSujet().getIdSujet().equals(idSujet)){
                if(isEtudiant) {
                    EquipeDto equipeDto1 = new EquipeDto();
                    equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
                    equipeDto.add(equipeDto1);
                }else if(equipe.getEtudiant().size() == equipe.getTailleEquipe()){
                    EquipeDto equipeDto1 = new EquipeDto();
                    equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
                    equipeDto.add(equipeDto1);
                }
            }
        }
        return equipeDto;
    }

    @Override
    public List<EquipeDto> getLockedEquipes(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("enseignant not found");
            throw new RuntimeException("enseignant not found !!!");
        }

        List<EquipeDto> equipeDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Equipe> equipePage = equipeRepository.findAll(pageableRequest);
        List<Equipe> equipeList = equipePage.getContent();
        for (Equipe equipe : equipeList) {

            if(equipe.getSujet().getEncadrant().getIdEnseignant().equals(enseignant.getIdEnseignant()) && equipe.getSujet().getLocked()){
                EquipeDto equipeDto1 = new EquipeDto();
                equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
                equipeDto.add(equipeDto1);
            }
        }
        return equipeDto;
    }

    @Override
    public EquipeDto addDriveLink(String username,EquipeDto equipeDto) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(equipeDto.getIdEquipe());

        if (EquipeEntity == null) {
            logger.info("equipe not found");
            throw new RuntimeException(equipeDto.getIdEquipe());
        }

        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("etudiant not found");
            throw new RuntimeException("etudiant not found !!!");
        }

        if(!EquipeEntity.getEtudiant().contains(etudiant)){
            logger.info("you cant add drive link to this equipe");
            throw new RuntimeException("you cant add drive link to this equipe");
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(equipeDto.getDriveLink(), HttpMethod.HEAD, entity, String.class);
        } catch (Exception e) {
            logger.error("Error checking the link: " + e.getMessage());
            throw new RuntimeException("Error checking the link: " + e.getMessage());
        }
        EquipeEntity.setDriveLink(equipeDto.getDriveLink());

        Equipe equipeeUpdated = equipeRepository.save(EquipeEntity);


        logger.info("Equipe updated successfully.");

        return modelMapper.map(equipeeUpdated, EquipeDto.class);
    }

    @Override
    public List<String> getEmailsOfEquipe(String username, EquipeDto equipeDto) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(equipeDto.getIdEquipe());
        if (EquipeEntity == null) {
            logger.warn("equipe not found");
            throw new RuntimeException(equipeDto.getIdEquipe());
        }

        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.warn("etudiant not found");
            throw new RuntimeException("etudiant not found !!!");
        }

        if(!EquipeEntity.getEtudiant().contains(etudiant)){
            logger.warn("you cant get emails of this equipe");
            throw new RuntimeException("you cant get emails of this equipe");
        }

        List<String> emails = new ArrayList<>();
        for (Etudiant etudiant1 : EquipeEntity.getEtudiant()) {
            if(!etudiant1.getEmail().equals(username)) {
                emails.add(etudiant1.getEmail());
            }
        }
        emails.add(EquipeEntity.getSujet().getEncadrant().getEmail());

        return emails;
    }

}
