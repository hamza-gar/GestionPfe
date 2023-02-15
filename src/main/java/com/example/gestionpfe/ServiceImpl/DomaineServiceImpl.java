package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Entities.Universite;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DomaineRepository;
import com.example.gestionpfe.Repositories.UniversiteRepository;
import com.example.gestionpfe.Services.DomaineService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DomaineServiceImpl implements DomaineService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(DomaineServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    DomaineRepository domaineRepository;

    @Autowired
    Utils util;

    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public DomaineDto addDomaine(DomaineDto domainDto) {
        Universite universiteEntity = universiteRepository.findByIdUniversite(domainDto.getIdUniversite());
        if(universiteEntity==null) {
            logger.error("universite not found");
            throw new UsernameNotFoundException(domainDto.getIdUniversite());
        }

        Domaine domaineEntity = new Domaine();
        domaineEntity.setUniversite(universiteEntity);
        domaineEntity.setNomDomaine(domainDto.getNomDomaine());
        domaineEntity.setEtudiant(domainDto.getEtudiant());

        Domaine newDomaine = domaineRepository.save(domaineEntity);

        DomaineDto newDomaineDto = new DomaineDto();
        newDomaineDto = modelMapper.map(newDomaine, DomaineDto.class);
        logger.info("domaine saved successfully");

        return newDomaineDto;
    }



    @Override
    public DomaineDto getDomaine(String nomDomaine) {
        Domaine DomaineEntity =  domaineRepository.findByNomDomaine(nomDomaine);
        if(DomaineEntity==null)throw new UsernameNotFoundException(nomDomaine);

        DomaineDto domaineDto = new DomaineDto();
        domaineDto = modelMapper.map(DomaineEntity, DomaineDto.class);
        logger.info("domaine found successfully");
        return domaineDto;
    }

    @Override
    public DomaineDto getDomaineById(String id) {
        Domaine DomaineEntity =  domaineRepository.findById(id);

        if(DomaineEntity == null)throw new UsernameNotFoundException(id);

        DomaineDto domaineDto = new DomaineDto();
        domaineDto = modelMapper.map(DomaineEntity, DomaineDto.class);
        logger.info("domaine found successfully");
        return domaineDto;
    }

    @Override
    public DomaineDto updateDomaine(long id, DomaineDto domainDto) {

        Domaine DomaineEntity =  domaineRepository.getDomaineById(id);
        Universite universiteEntity = universiteRepository.findByIdUniversite(domainDto.getIdUniversite());

        if(DomaineEntity == null)throw new UsernameNotFoundException(String.valueOf(id));

        if (DomaineEntity.getNomDomaine() != null) {
            DomaineEntity.setNomDomaine(domainDto.getNomDomaine());
        }
        if (DomaineEntity.getEtudiant() != null) {
            DomaineEntity.setEtudiant(domainDto.getEtudiant());
        }
        if (universiteEntity != null) {
            DomaineEntity.setUniversite(universiteEntity);
        }


        Domaine domaineUpdated = domaineRepository.save(DomaineEntity);

        DomaineDto newdomaineDto = new DomaineDto();
        newdomaineDto = modelMapper.map(domaineUpdated, DomaineDto.class);
        logger.info("domaine updated successfully");

        return newdomaineDto;
    }

    @Override
    public void deleteDomaine(long id) {
        Domaine DomaineEntity =  domaineRepository.getDomaineById(id);

        if(DomaineEntity == null)throw new UsernameNotFoundException(String.valueOf(id));

        domaineRepository.delete(DomaineEntity);
    }

    @Override
    public List<DomaineDto> getAllDomaines(int page, int limit) {
        List<DomaineDto>domainesDto = new ArrayList<>();

        Pageable pageableRequest =  PageRequest.of(page,limit);

        Page<Domaine> domainePages = domaineRepository.findAll(pageableRequest);

        List<Domaine>domaines = domainePages.getContent();

        for(Domaine domaineEntity:domaines){
            DomaineDto domaine = new DomaineDto();
            domaine = modelMapper.map(domaineEntity, DomaineDto.class);
            domaine.setNomUniversite(domaineEntity.getUniversite().getNomUniversite());

            domainesDto.add(domaine);
        }
        logger.info("All domaines found successfully");

        return domainesDto;
    }
}
