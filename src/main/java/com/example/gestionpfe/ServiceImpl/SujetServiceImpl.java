package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Filiere;
import com.example.gestionpfe.Entities.Sujet;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.FiliereRepository;
import com.example.gestionpfe.Repositories.SujetRepository;
import com.example.gestionpfe.Responses.FiliereResponse;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.SujetService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public SujetDto getSujetById(String id) {
    Sujet sujetEntity = sujetRepository.findByIdSujet(id);

    if (sujetEntity == null) throw new RuntimeException(id);

    SujetDto sujetDto = new SujetDto();
    sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
    logger.info("sujet found successfully");

    return sujetDto;
    }

    @Override
    public SujetDto updateSujet(String mailEnseignant,String id, SujetDto sujetDTO) {
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

        if (!sujetEntity.getEncadrant().getEmail().equals(mailEnseignant)){
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
    public void deleteSujet(String id) {
        Sujet sujetEntity = sujetRepository.findByIdSujet(id);
        if (sujetEntity == null) throw new RuntimeException(id);
        logger.info("sujet deleted successfully");
        sujetRepository.delete(sujetEntity);
    }

    @Override
    public List<SujetDto> getAllSujets(int page, int limit) {
        List<SujetDto> sujetDtoList = new ArrayList<>();

        Pageable pageableRequest =  PageRequest.of(page,limit);

        Page<Sujet> SujetPages = sujetRepository.findAll(pageableRequest);
        List<Sujet> sujetList = SujetPages.getContent();

        for (Sujet sujetEntity : sujetList) {
            SujetDto sujetDto = new SujetDto();
            sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
            sujetDtoList.add(sujetDto);
        }
        logger.info("sujet list found successfully");
        return sujetDtoList;
    }

    @Override
    public List<SujetDto> getAllSujetsByFiliere(String idFiliere, int page, int limit) {
        List<SujetDto> sujetDtoList = new ArrayList<>();
        Page<Sujet> SujetPages = sujetRepository.findAll(PageRequest.of(page,limit));
        List<Sujet> sujetList = SujetPages.getContent();
        Filiere filiere = filiereRepository.findByIdFiliere(idFiliere);
        if (filiere == null) {
            logger.info("filiere not found");
            throw new RuntimeException(idFiliere);
        }
        for (Sujet sujetEntity : sujetList) {
            if(sujetEntity.getEncadrant().getFiliere().getNomFiliere().equals(filiere.getNomFiliere())){
                SujetDto sujetDto = new SujetDto();
                sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
                sujetDtoList.add(sujetDto);
            }
        }
        logger.info("sujet list found successfully.");
        return sujetDtoList;
    }
}
