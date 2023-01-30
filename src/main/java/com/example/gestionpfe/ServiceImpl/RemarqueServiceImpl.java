package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.RemarqueDto;
import com.example.gestionpfe.Entities.Remarque;
import com.example.gestionpfe.Repositories.RemarqueRepository;
import com.example.gestionpfe.Services.RemarqueService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemarqueServiceImpl implements RemarqueService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(RemarqueServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RemarqueRepository remarqueRepository;

    @Autowired
    Utils util;

    @Override
    public RemarqueDto addRemarque(RemarqueDto remarqueDto) {
        Remarque checkRemarque = remarqueRepository.findByIdRemarque(remarqueDto.getIdRemarque());

        if(checkRemarque!=null) throw new RuntimeException("remarque deja exist !!!");
        Remarque remarqueEntity = new Remarque();
        remarqueEntity = modelMapper.map(remarqueDto, Remarque.class);

Remarque newRemarque = remarqueRepository.save(remarqueEntity);

            RemarqueDto newRemarqueDto = new RemarqueDto();
            newRemarqueDto = modelMapper.map(newRemarque, RemarqueDto.class);
            logger.info("remarque found successfully");

            return newRemarqueDto;
    }

    @Override
    public RemarqueDto getRemarqueByIdRemarque(String id) {
        Remarque remarqueEntity =  remarqueRepository.findByIdRemarque(id);
        if(remarqueEntity==null)throw new RuntimeException(id);

        RemarqueDto remarqueDto = new RemarqueDto();
        remarqueDto = modelMapper.map(remarqueEntity, RemarqueDto.class);
        logger.info("remarque found successfully");

        return remarqueDto;
    }

    @Override
    public RemarqueDto updateRemarque(String id, RemarqueDto remarque) {
        Remarque remarqueEntity = remarqueRepository.findByIdRemarque(id);
        if(remarqueEntity==null)throw new RuntimeException(id);

        remarqueEntity.setRemarque(remarque.getIdRemarque());
        remarqueEntity.setNote(remarque.getNote());

        Remarque updatedRemarque = remarqueRepository.save(remarqueEntity);
        RemarqueDto updatedRemarqueDto = new RemarqueDto();
        updatedRemarqueDto = modelMapper.map(updatedRemarque, RemarqueDto.class);
        logger.info("remarque updated successfully");

        return updatedRemarqueDto;
    }

    @Override
    public void deleteRemarque(String id) {
        Remarque remarqueEntity = remarqueRepository.findByIdRemarque(id);
        if(remarqueEntity==null)throw new RuntimeException(id);
        remarqueRepository.delete(remarqueEntity);
        logger.info("remarque deleted successfully");
    }

    @Override
    public List<RemarqueDto> getAllRemarques( int page, int limit) {
        List<RemarqueDto>remarqueDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        List<Remarque> remarqueEntity = remarqueRepository.findAll(pageableRequest).getContent();
        for(Remarque remarque: remarqueEntity){
            remarqueDto.add(modelMapper.map(remarque, RemarqueDto.class));
        }

        return remarqueDto;
    }

}
