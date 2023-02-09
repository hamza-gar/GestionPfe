package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EquipeRepository;
import com.example.gestionpfe.Repositories.FiliereRepository;
import com.example.gestionpfe.Repositories.SujetRepository;
import com.example.gestionpfe.Responses.FiliereResponse;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.EquipeService;
import com.example.gestionpfe.Services.SujetService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SujetServiceImpl implements SujetService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(SujetServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    Utils util;

    @Autowired
    SujetRepository sujetRepository;

    @Autowired
    FiliereRepository filiereRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EquipeRepository equipeRepository;

    @Autowired
    EquipeService equipeService;

    @Override
    public SujetDto addSujet(SujetDto sujetDTO, String username) {
        Sujet checkSujet = sujetRepository.findByNomSujet(sujetDTO.getNomSujet());
        if (checkSujet != null) {
            logger.info("sujet already exists");
            throw new RuntimeException("sujet deja exist !!!");
        }

        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("enseignant not found");
            throw new RuntimeException("enseignant not found");
        }

        Sujet sujetEntity = new Sujet();
        sujetEntity = modelMapper.map(sujetDTO, Sujet.class);
        sujetEntity.setEncadrant(enseignant);
        sujetEntity.setIdSujet(util.generateUserId(32));
        sujetEntity.setLocked(false);
        sujetEntity.setDone(false);

        Sujet newSujet = sujetRepository.save(sujetEntity);

        SujetDto newSujetDto = new SujetDto();
        newSujetDto = modelMapper.map(newSujet, SujetDto.class);
        logger.info("sujet found successfully");

        return newSujetDto;
    }

    @Override
    public SujetDto getSujet(String nomSujet) {
        Sujet sujetEntity = sujetRepository.findByNomSujet(nomSujet);
        if (sujetEntity == null) throw new RuntimeException(nomSujet);

        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
        logger.info("sujet found successfully");

        return sujetDto;
    }

    @Override
    public SujetDto getSujetByIdSujet(String filiere) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(filiere);
        if (sujetEntity == null) throw new RuntimeException(filiere);

        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
        logger.info("sujet found successfully");
        return sujetDto;
    }

    @Override
    public SujetDto getSujetById(String id) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(id);

        if (sujetEntity == null) throw new RuntimeException(id);

        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
        logger.info("sujet found successfully");

        return sujetDto;
    }

    @Override
    public SujetDto updateSujet(String mailEnseignant, String id, SujetDto sujetDTO) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(id);
        if (sujetEntity == null) {
            logger.info("sujet not found");
            throw new RuntimeException(id);
        }
        Enseignant enseignant = enseignantRepository.findByEmail(mailEnseignant);
        if (enseignant == null) {
            logger.info("enseignant not found");
            throw new RuntimeException("enseignant not found");
        }

        if (!sujetEntity.getEncadrant().getEmail().equals(mailEnseignant)) {
            logger.info("you are not the owner of this sujet");
            throw new RuntimeException("you are not the owner of this sujet");
        }

        sujetEntity.setNomSujet(sujetDTO.getNomSujet());


        Sujet updatedSujet = sujetRepository.save(sujetEntity);

        SujetDto updatedSujetDto = new SujetDto();
        updatedSujetDto = modelMapper.map(updatedSujet, SujetDto.class);
        logger.info("sujet updated successfully");

        return updatedSujetDto;
    }

    @Override
    public SujetDto lockSujet(String mailEnseignant, String idSujet, String idEquipe) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(idSujet);
        if (sujetEntity == null) {
            logger.info("sujet not found");
            throw new RuntimeException(idSujet);
        }
        logger.info("sujet found successfully");
        if (!sujetEntity.getEncadrant().getEmail().equals(mailEnseignant)) {
            logger.info("you are the owner of this sujet");
            throw new RuntimeException("you are the owner of this sujet");
        }
        logger.info("you are the owner of this sujet");
        if (!sujetEntity.getEquipe().contains(equipeRepository.findByIdEquipe(idEquipe))) {
            logger.info("this equipe is not in this sujet");
            throw new RuntimeException("this equipe is not in this sujet");
        }
        logger.info("this equipe is in this sujet");
        List<Equipe> toDelete = new ArrayList<>();
        List<Equipe> equipeList = new ArrayList<>();
        for (Equipe equipe : sujetEntity.getEquipe()) {
            if (!equipe.getIdEquipe().equals(idEquipe)) {
                toDelete.add(equipe);
            } else {
                if (equipe.getTailleEquipe() == equipe.getEtudiant().size()) {
                    equipeList.add(equipe);
                } else {
                    logger.info("this equipe is not full");
                    throw new RuntimeException("this equipe is not full");
                }
            }
        }

        equipeRepository.deleteAll(toDelete);


        for (Etudiant etudiant : equipeList.get(0).getEtudiant()) {
            for (Equipe equipe : etudiant.getEquipe()) {
                if (!equipe.getIdEquipe().equals(idEquipe)) {
                    equipeService.removeEtudiant(equipe.getIdEquipe(), etudiant.getIdEtudiant());
                }
            }
        }
        sujetEntity.setDone(false);
        sujetEntity.setEquipe(equipeList);
        sujetEntity.setLocked(true);
        Sujet sujet = sujetRepository.save(sujetEntity);
        logger.info("sujet locked successfully");
        return modelMapper.map(sujet, SujetDto.class);
    }

    @Override
    public SujetDto validateSujet(String username, SujetDto sujetDto, Boolean done) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(sujetDto.getIdSujet());
        if (sujetEntity == null) {
            logger.warn("sujet not found");
            throw new RuntimeException(sujetDto.getIdSujet());
        }
        logger.info("sujet found successfully");
        if (!sujetEntity.getEncadrant().getEmail().equals(username)) {
            logger.warn("you are not the owner of this sujet");
            throw new RuntimeException("you are not the owner of this sujet");
        }
        logger.info("you are the owner of this sujet");
        /* TODO: Notify the students that their sujet is validated */
        sujetEntity.setDone(done);
        Sujet response = sujetRepository.save(sujetEntity);
        logger.info("sujet validated successfully");

        return modelMapper.map(response, SujetDto.class);
    }

    @Override
    public void deleteSujet(String id) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(id);
        if (sujetEntity == null) throw new RuntimeException(id);
        logger.info("sujet deleted successfully");
        sujetRepository.delete(sujetEntity);
    }

    @Override
    public List<SujetDto> getAllSujets(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);


        List<SujetDto> sujetDtoList = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Sujet> sujetPages;
        if (enseignant == null) {
            logger.warn("enseignant not found");
            sujetPages = sujetRepository.findAllByLocked(false, pageableRequest);
            for (Sujet sujetEntity : sujetPages) {
                SujetDto sujetDto = new SujetDto();
                sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
                sujetDtoList.add(sujetDto);
            }
        } else {
            logger.info("enseignant found successfully");
            sujetPages = sujetRepository.findAll(pageableRequest);
            for (Sujet sujetEntity : sujetPages) {
                SujetDto sujetDto = new SujetDto();
                sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
                sujetDtoList.add(sujetDto);
            }
        }


        logger.info("sujet list found successfully");
        return sujetDtoList;
    }

    @Override
    public List<SujetDto> getAllSujetsByFiliere(String idFiliere, int page, int limit) {
        List<SujetDto> sujetDtoList = new ArrayList<>();
        Page<Sujet> SujetPages = sujetRepository.findAll(PageRequest.of(page, limit));
        List<Sujet> sujetList = SujetPages.getContent();

        Filiere filiere = filiereRepository.findByIdFiliere(idFiliere);
        if (filiere == null) {
            logger.info("filiere not found");
            throw new RuntimeException(idFiliere);
        }
        for (Sujet sujetEntity : sujetList) {
            if (sujetEntity.getEncadrant().getFiliere().getNomFiliere().equals(filiere.getNomFiliere())) {
                SujetDto sujetDto = new SujetDto();
                sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
                sujetDtoList.add(sujetDto);
            }
        }
        logger.info("sujet list found successfully.");
        return sujetDtoList;
    }

    @Override
    public long countSujets(String username) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("enseignant not found");
            return sujetRepository.countAllByLocked(false);
        }
        return sujetRepository.count();
    }

    @Override
    public List<SujetDto> getAllMySujets(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("enseignant not found");
            throw new RuntimeException(username);
        }
        List<SujetDto> sujetDtoList = new ArrayList<>();
        Page<Sujet> SujetPages = sujetRepository.findAllByEncadrant_IdEnseignant(enseignant.getIdEnseignant(), PageRequest.of(page, limit));
        for (Sujet sujetEntity : SujetPages) {
            SujetDto sujetDto = new SujetDto();
            sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
            sujetDtoList.add(sujetDto);
        }
        logger.info("sujet list found successfully.");
        return sujetDtoList;
    }

}