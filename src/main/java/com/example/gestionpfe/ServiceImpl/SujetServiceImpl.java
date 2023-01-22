package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.Sujet;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.SujetRepository;
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
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    Utils util;

    @Autowired
    SujetRepository sujetRepository;

    @Override
    public SujetDto addSujet(SujetDto sujetDTO) {
    Sujet checkSujet = sujetRepository.findByNomSujet(sujetDTO.getNomSujet());
    if (checkSujet != null) throw new RuntimeException("sujet deja exist !!!");
    Sujet sujetEntity = new Sujet();
    sujetEntity = modelMapper.map(sujetDTO, Sujet.class);
    logger.info("sujetEntity : " + sujetEntity);

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
    Sujet sujetEntity = sujetRepository.findById(id);

    if (sujetEntity == null) throw new RuntimeException(id);

    SujetDto sujetDto = new SujetDto();
    sujetDto = modelMapper.map(sujetEntity, SujetDto.class);
    logger.info("sujet found successfully");

    return sujetDto;
    }

    @Override
    public SujetDto updateSujet(String id, SujetDto sujetDTO) {
        Sujet sujetEntity = sujetRepository.findById(id);
        if (sujetEntity == null) throw new RuntimeException(id);

        sujetEntity.setNomSujet(sujetDTO.getNomSujet());

        Sujet updatedSujet = sujetRepository.save(sujetEntity);

        SujetDto updatedSujetDto = new SujetDto();
        updatedSujetDto = modelMapper.map(updatedSujet, SujetDto.class);
        logger.info("sujet updated successfully");

        return updatedSujetDto;
    }

    @Override
    public void deleteSujet(String id) {
        Sujet sujetEntity = sujetRepository.findById(id);
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
}
