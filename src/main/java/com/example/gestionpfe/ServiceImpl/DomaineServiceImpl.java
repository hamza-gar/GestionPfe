package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DomaineRepository;
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

    @Override
    public DomaineDto addDomaine(DomaineDto domainDto) {
        Domaine checkDomaine = domaineRepository.findByNomDomaine(domainDto.getNomDomaine());

        if(checkDomaine!=null) throw new RuntimeException("domaine deja exist !!!");
        Domaine domaineEntity = new Domaine();
        domaineEntity = modelMapper.map(domainDto, Domaine.class);



        Domaine newDomaine = domaineRepository.save(domaineEntity);

        DomaineDto newDomaineDto = new DomaineDto();
        newDomaineDto = modelMapper.map(newDomaine, DomaineDto.class);
        logger.info("domaine found successfully");

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
    public DomaineDto updateDomaine(String id, DomaineDto domainDto) {

        Domaine DomaineEntity =  domaineRepository.findById(id);

        if(DomaineEntity == null)throw new UsernameNotFoundException(id);

        /*TODO: optional fields.*/
        DomaineEntity.setNomDomaine(domainDto.getNomDomaine());
        DomaineEntity.setEtablissement(domainDto.getEtablissement());

        Domaine domaineUpdated = domaineRepository.save(DomaineEntity);

        DomaineDto newdomaineDto = new DomaineDto();
        newdomaineDto = modelMapper.map(domaineUpdated, DomaineDto.class);
        logger.info("domaine updated successfully");

        return newdomaineDto;
    }

    @Override
    public void deleteDomaine(String id) {
        Domaine DomaineEntity =  domaineRepository.findById(id);

        if(DomaineEntity == null)throw new UsernameNotFoundException(id);

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


            domainesDto.add(domaine);
        }
        logger.info("All domaines found successfully");

        return domainesDto;
    }
}
