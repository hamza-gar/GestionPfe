package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.DepartementDto;
import com.example.gestionpfe.Dto.FiliereDto;

import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Filiere;
import com.example.gestionpfe.InitialUsersSetup;

import com.example.gestionpfe.Repositories.FiliereRepository;
import com.example.gestionpfe.Services.FiliereService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(FiliereServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    FiliereRepository filiereRepository;

    @Autowired
    Utils util;

    @Override
    public FiliereDto addFiliere(FiliereDto filiereDTO) {
        Filiere checkFiliere = filiereRepository.findByNomFiliere(filiereDTO.getNomFiliere());

        if(checkFiliere!=null) throw new RuntimeException("filiere deja exist !!!");
        Filiere filiereEntity = new Filiere();
        filiereEntity = modelMapper.map(filiereDTO, Filiere.class);
        filiereEntity.setIdFiliere(util.generateUserId(32));


        Filiere newFiliere = filiereRepository.save(filiereEntity);

        FiliereDto newFiliereDto = new FiliereDto();
        newFiliereDto = modelMapper.map(newFiliere, FiliereDto.class);
        logger.info("filiere found successfully");

        return newFiliereDto;
    }

    @Override
    public FiliereDto getFiliere(String nomFiliere) {
        Filiere filiereEntity =  filiereRepository.findByNomFiliere(nomFiliere);
        if(filiereEntity==null)throw new UsernameNotFoundException(nomFiliere);

        FiliereDto filiereDto = new FiliereDto();
        filiereDto = modelMapper.map(filiereEntity, FiliereDto.class);
        logger.info("filiere found successfully");
        return filiereDto;
    }

    @Override
    public FiliereDto getFiliereByIdFiliere(String id) {
        Filiere filiereEntity =  filiereRepository.findByIdFiliere(id);

        if(filiereEntity == null)throw new UsernameNotFoundException(id);

        FiliereDto filiereDto = new FiliereDto();
        filiereDto = modelMapper.map(filiereEntity, FiliereDto.class);
        logger.info("filiere found successfully");
        return filiereDto;
    }

    @Override
    public FiliereDto updateFiliere(String id, FiliereDto filiereDTO) {
        Filiere filiereEntity =  filiereRepository.findByIdFiliere(id);

        if(filiereEntity == null)throw new UsernameNotFoundException(id);

        /*TODO: optional fields.*/
        filiereEntity.setNomFiliere(filiereDTO.getNomFiliere());

        Filiere filereUpdated = filiereRepository.save(filiereEntity);

        FiliereDto newFiliereDto = new FiliereDto();
        newFiliereDto = modelMapper.map(newFiliereDto, FiliereDto.class);
        logger.info("filiere updated successfully");

        return newFiliereDto;
    }

    @Override
    public void deleteFiliere(String id) {
        Filiere filiereEntity =  filiereRepository.findByIdFiliere(id);

        if(filiereEntity == null)throw new UsernameNotFoundException(id);
        logger.info("filiere deleted successfully");
        filiereRepository.delete(filiereEntity);
    }

    @Override
    public List<FiliereDto> getAllFilieres(int page, int limit) {
        List<FiliereDto>filieresDto = new ArrayList<>();
        Pageable pageableRequest =  PageRequest.of(page,limit);

        Page<Filiere> FilierePages = filiereRepository.findAll(pageableRequest);

        List<Filiere>filieres = FilierePages.getContent();

        for(Filiere filiereEntity:filieres){
            FiliereDto filiere = new FiliereDto();
            logger.info("filiere :"+filiereEntity.getNomFiliere()+" found successfully");

            filiere = modelMapper.map(filiereEntity, FiliereDto.class);


            filieresDto.add(filiere);
        }
        logger.info("All filieres found successfully");

        return filieresDto;
    }


}

