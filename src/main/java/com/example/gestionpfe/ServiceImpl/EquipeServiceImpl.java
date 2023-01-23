package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Dto.EquipeDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.EquipeRepository;
import com.example.gestionpfe.Services.EquipeService;
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
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    Utils util;

    @Override
    public EquipeDto addEquipe(EquipeDto equipeDto) {
        Equipe checkEquipe = equipeRepository.findByIdEquipe(equipeDto.getIdEquipe());

        if(checkEquipe!=null) throw new RuntimeException("equipe deja exist !!!");
        Equipe equipeEntity = new Equipe();
        equipeEntity = modelMapper.map(equipeDto, Equipe.class);



        Equipe newEquipe = equipeRepository.save(equipeEntity);

        EquipeDto newEquipeDto = new EquipeDto();
        newEquipeDto = modelMapper.map(newEquipe, EquipeDto.class);
        logger.info("equipe found successfully");

        return newEquipeDto;
    }

    @Override
    public EquipeDto getEquie(String nomDomaine) {
        Equipe EquipeEntity =  equipeRepository.findByIdEquipe(nomDomaine);
        if(EquipeEntity==null)throw new UsernameNotFoundException(nomDomaine);

        EquipeDto equipeDto = new EquipeDto();
        equipeDto = modelMapper.map(EquipeEntity, EquipeDto.class);
        logger.info("domaine found successfully");
        return equipeDto;
    }

    @Override
    public EquipeDto getEquipeById(String id) {
        Equipe EquipeEntity =  equipeRepository.findByIdEquipe(id);

        if(EquipeEntity == null)throw new UsernameNotFoundException(id);

        EquipeDto equipeDto = new EquipeDto();
        equipeDto = modelMapper.map(EquipeEntity, EquipeDto.class);
        logger.info("domaine found successfully");
        return equipeDto;
    }

    @Override
    public EquipeDto updateEquipe(String id, EquipeDto equipeDto) {
        Equipe EquipeEntity =  equipeRepository.findByIdEquipe(id);

        if(EquipeEntity == null)throw new UsernameNotFoundException(id);

        /*TODO: optional fields.*/
        EquipeEntity.setTailleEquipe(equipeDto.getTailleEquipe());

        Equipe equipeeUpdated = equipeRepository.save(EquipeEntity);

        EquipeDto newequipeDto = new EquipeDto();
        newequipeDto = modelMapper.map(equipeeUpdated, EquipeDto.class);
        logger.info("domaine updated successfully");

        return newequipeDto;
    }

    @Override
    public void deleteEquipe(String id) {
        Equipe EquipeEntity =  equipeRepository.findByIdEquipe(id);

        if(EquipeEntity == null)throw new UsernameNotFoundException(id);

        equipeRepository.delete(EquipeEntity);
        logger.info("domaine deleted successfully");
    }

    @Override
    public List<EquipeDto> getAllEquipes(int page, int limit) {
        List<EquipeDto> equipeDto=new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Equipe> equipePage = equipeRepository.findAll(pageableRequest);
        List<Equipe> equipeList = equipePage.getContent();
        for(Equipe equipe:equipeList){
            EquipeDto equipeDto1 = new EquipeDto();
            equipeDto1 = modelMapper.map(equipe, EquipeDto.class);
            equipeDto.add(equipeDto1);
        }
        return equipeDto;
    }

}
