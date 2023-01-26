package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Dto.EquipeDto;
import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Sujet;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.EquipeRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipeServiceImpl implements EquipeService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(EquipeServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    Utils util;

    @Autowired
    SujetService sujetService;

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    SujetRepository sujetRepository;


    @Override
    public EquipeDto addEquipe(String sujetId, EquipeDto equipeDto) {

        /* TODO: check if the student is already affiliated to an already existing team */

        if (etudiantService.etudiantAlreadyInSujet(equipeDto.getEtudiant().get(0).getIdEtudiant(), sujetId)) {
            logger.info("This Etudiant is already existing in a team for this subject.");
            throw new RuntimeException("This Etudiant is already existing in a team for this subject.");
        }


        SujetDto sujet = sujetService.getSujetById(sujetId);
        if (sujet == null) {
            logger.info("sujet not found");
            throw new RuntimeException("sujet not found !!!");
        }
        if (etudiantService.etudiantIn3Sujets(equipeDto.getEtudiant().get(0).getIdEtudiant(), sujet.getEncadrant().getIdEnseignant())) {
            logger.info("This Etudiant is already existing in 3 teams.");
            throw new RuntimeException("This Etudiant is already existing in 3 teams.");
        }
        Sujet sujetEntity = new Sujet();
        sujetEntity = modelMapper.map(sujet, Sujet.class);

        logger.info("Sujet :" + sujetEntity.toString());

        Equipe equipeEntity = new Equipe();
        equipeEntity = modelMapper.map(equipeDto, Equipe.class);

        equipeEntity.setIdEquipe(util.generateUserId(32));
        logger.info("Sujet :" + sujetEntity.toString());
        equipeEntity.setSujet(sujetEntity);
        equipeEntity.setTailleEquipe(sujetEntity.getTailleEquipe());
        logger.info("equipe :" + equipeEntity.toString());
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
    public void deleteEquipe(String id) {
        Equipe EquipeEntity = equipeRepository.findByIdEquipe(id);

        if (EquipeEntity == null) throw new UsernameNotFoundException(id);

        equipeRepository.delete(EquipeEntity);
        logger.info("equipe deleted successfully.");
    }

    @Override
    public List<EquipeDto> getAllEquipes(int page, int limit) {
        List<EquipeDto> equipeDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Equipe> equipePage = equipeRepository.findAll(pageableRequest);
        List<Equipe> equipeList = equipePage.getContent();
        for (Equipe equipe : equipeList) {
            EquipeDto equipeDto1 = new EquipeDto();
            equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
            equipeDto.add(equipeDto1);
        }
        return equipeDto;
    }

}
