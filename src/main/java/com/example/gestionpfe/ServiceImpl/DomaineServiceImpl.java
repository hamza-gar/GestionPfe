package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Repositories.DomaineRepository;
import com.example.gestionpfe.Services.DomaineService;
import com.example.gestionpfe.Shared.Utils;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    DomaineRepository domaineRepository;

    @Autowired
    Utils util;

    @Override
    public DomaineDto addDomaine(DomaineDto domainDto) {
        Domaine checkDomaine = domaineRepository.findByNomDomaine(domainDto.getNomDomaine());

        if(checkDomaine!=null) throw new RuntimeException("domaine deja exist !!!");
        Domaine domaineEntity = new Domaine();
        BeanUtils.copyProperties(domainDto,domaineEntity);


        Domaine newDomaine = domaineRepository.save(domaineEntity);

        DomaineDto newDomaineDto = new DomaineDto();

        BeanUtils.copyProperties(newDomaine,newDomaineDto);

        return newDomaineDto;
    }

    @Override
    public DomaineDto getDomaine(String nomDomaine) {
        Domaine DomaineEntity =  domaineRepository.findByNomDomaine(nomDomaine);
        if(DomaineEntity==null)throw new UsernameNotFoundException(nomDomaine);

        DomaineDto domaineDto = new DomaineDto();

        BeanUtils.copyProperties(DomaineEntity,domaineDto);
        return domaineDto;
    }

    @Override
    public DomaineDto getDomaineById(String id) {
        Domaine DomaineEntity =  domaineRepository.findById(id);

        if(DomaineEntity == null)throw new UsernameNotFoundException(id);

        DomaineDto domaineDto = new DomaineDto();

        BeanUtils.copyProperties(DomaineEntity,domaineDto);
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

        BeanUtils.copyProperties(domaineUpdated,newdomaineDto);

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
            BeanUtils.copyProperties(domaineEntity,domaine);

            domainesDto.add(domaine);
        }

        return domainesDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Domaine DomaineEntity =  domaineRepository.findByNomDomaine(username);

        if(DomaineEntity==null)throw new UsernameNotFoundException(username);

        return new User(DomaineEntity.getNomDomaine(),DomaineEntity.getEtablissement(),new ArrayList<>());
    }
}
