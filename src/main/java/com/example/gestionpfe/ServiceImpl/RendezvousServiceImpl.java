package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.RendezvousDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Rendezvous;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.AdminRepository;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.RendezvousRepository;
import com.example.gestionpfe.Services.RendezvousService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RendezvousServiceImpl implements RendezvousService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(RendezvousServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    RendezvousRepository rendezvousRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public RendezvousDto addRendezvous(RendezvousDto rendezvousDto) {
        Rendezvous checkRendezvous = rendezvousRepository.findByIdRendezvous(rendezvousDto.getIdRendezvous());
        if (checkRendezvous != null) throw new RuntimeException("Rendezvous deja exist !!!");
        Rendezvous rendezvousEntity = new Rendezvous();
        rendezvousEntity = modelMapper.map(rendezvousDto, Rendezvous.class);
        logger.info("RendezvousEntity mapping: " + rendezvousEntity.toString());

        rendezvousEntity.setIdRendezvous(util.generateUserId(32));

        Rendezvous newRendezvous = rendezvousRepository.save(rendezvousEntity);

        RendezvousDto newRendezvousDto = new RendezvousDto();
        newRendezvousDto = modelMapper.map(newRendezvous, RendezvousDto.class);
        logger.info("Rendezvous found successfully");

        return newRendezvousDto;
    }

    @Override
    public RendezvousDto getRendezvousByDate(Date dateRendezvous) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByDateRendezvous(dateRendezvous);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");
        RendezvousDto rendezvousDto = new RendezvousDto();
        rendezvousDto = modelMapper.map(rendezvousEntity, RendezvousDto.class);
        logger.info("Rendezvous found successfully");
        return rendezvousDto;
    }

    @Override
    public RendezvousDto getRendezvousByIdRendezvous(String username, String id) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(id);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");

        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant != null) {
            if (etudiant.getEquipe().get(0).getSujet().getNomSujet().equals(rendezvousEntity.getEquipe().getSujet().getNomSujet())) {
                logger.info("Rendezvous found successfully");
                return modelMapper.map(rendezvousEntity, RendezvousDto.class);
            }
        } else {
            if (rendezvousEntity.getEncadrant().getEmail().equals(username)){
                logger.info("Rendezvous found successfully");
                return modelMapper.map(rendezvousEntity, RendezvousDto.class);
            }
        }
        logger.info("Not authorized !!!");
        throw new RuntimeException("Not authorized !!!");
    }

    @Override
    public RendezvousDto updateRendezvous(String id, RendezvousDto rendezvous) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(id);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");

        rendezvousEntity.setDateRendezvous(rendezvous.getDateRendezvous());

        Rendezvous updatedRendezvous = rendezvousRepository.save(rendezvousEntity);

        RendezvousDto updatedRendezvousDto = new RendezvousDto();
        updatedRendezvousDto = modelMapper.map(updatedRendezvous, RendezvousDto.class);
        logger.info("Rendezvous updated successfully");
        return updatedRendezvousDto;
    }

    @Override
    public void deleteRendezvous(String id) {
        Rendezvous rendezvousEntity = rendezvousRepository.findByIdRendezvous(id);
        if (rendezvousEntity == null) throw new RuntimeException("Rendezvous not found !!!");
        rendezvousRepository.delete(rendezvousEntity);
        logger.info("Rendezvous deleted successfully");
    }

    @Override
    public List<RendezvousDto> getAllRendezvous(String username,int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if(enseignant == null) {
            logger.info("Not authorized !!!");
            throw new RuntimeException("Not authorized !!!");
        }

        List<RendezvousDto> rendezvousDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Rendezvous> rendezvousPage = rendezvousRepository.findAll(pageableRequest);

        List<Rendezvous> rendezvous = rendezvousPage.getContent();

        for (Rendezvous rendezvousEntity : rendezvous) {
            RendezvousDto rendezvousDto = new RendezvousDto();
            if(rendezvousEntity.getEncadrant().getEmail().equals(username)){
                rendezvousDto = modelMapper.map(rendezvousEntity, RendezvousDto.class);
                rendezvousDtos.add(rendezvousDto);
            }
        }
        logger.info("all Rendezvous found successfully");
        return rendezvousDtos;
    }

}
