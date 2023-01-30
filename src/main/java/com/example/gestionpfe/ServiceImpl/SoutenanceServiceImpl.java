package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.SoutenanceRepository;
import com.example.gestionpfe.Services.SoutenanceService;
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
import java.util.List;

@Service
public class SoutenanceServiceImpl implements SoutenanceService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(SoutenanceServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SoutenanceRepository soutenanceRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EtudiantRepository etudiantRepository;


    @Override
    public SoutenanceDto addSoutenance(String username) {
        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("Etudiant not found");
            throw new RuntimeException("Etudiant not found");
        }
        List<Equipe> equipes = etudiant.getEquipe();
        if(equipes.size() == 0){
            logger.info("This etudiant doesnt belong to any team.");
            throw new RuntimeException("This etudiant doesnt belong to any team.");
        }
        if(equipes.size() != 1){
            logger.info("This etudiant belongs to more than one team.");
            throw new RuntimeException("This etudiant belongs to more than one team.");
        }

        Equipe equipe = equipes.get(0);
        if (equipe.getSujet().getLocked() == false) {
            logger.info("This team doesnt have a subject yet.");
            throw new RuntimeException("This team doesnt have a subject yet.");
        }
        if (equipe.getSujet().getSoutenance() != null) {
            logger.info("This team already has a soutenance.");
            throw new RuntimeException("This team already has a soutenance.");
        }
        Soutenance soutenance = new Soutenance();
        soutenance.setIdSoutenance(util.generateUserId(30));
        soutenance.setDateSoutenance(null);
        Soutenance soutenanceSaved = soutenanceRepository.save(soutenance);
        logger.info("Soutenance saved successfully");
        SoutenanceDto soutenanceDto = modelMapper.map(soutenanceSaved, SoutenanceDto.class);
        return soutenanceDto;
    }

    @Override
    public SoutenanceDto getSoutenanceByIdSoutenance(String username, String id) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        SoutenanceDto soutenanceDto = modelMapper.map(soutenance, SoutenanceDto.class);
        return soutenanceDto;
    }

    @Override
    public SoutenanceDto updateSoutenance(String id, SoutenanceDto soutenanceDto) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }

        soutenance.setDateSoutenance(soutenanceDto.getDateSoutenance());
        Soutenance soutenanceSaved = soutenanceRepository.save(soutenance);
        logger.info("Soutenance updated successfully");
        SoutenanceDto soutenanceDtoUpdated = modelMapper.map(soutenanceSaved, SoutenanceDto.class);
        return soutenanceDtoUpdated;
    }

    @Override
    public void deleteSoutenance(String id) {
        Soutenance soutenance = soutenanceRepository.findByIdSoutenance(id);
        if (soutenance == null) {
            logger.info("Soutenance not found");
            throw new RuntimeException("Soutenance not found");
        }
        soutenanceRepository.delete(soutenance);
        logger.info("Soutenance deleted successfully");
    }

    @Override
    public List<SoutenanceDto> getAllSoutenance(String username, int page, int limit) {
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.info("Enseignant not found");
            throw new RuntimeException("Enseignant not found");
        }
        List<SoutenanceDto> soutenanceDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Soutenance> soutenancePage = soutenanceRepository.findAll(pageableRequest);

        List<Soutenance> soutenances = soutenancePage.getContent();
        for (Soutenance soutenance : soutenances) {
            SoutenanceDto soutenanceDto1 = modelMapper.map(soutenance, SoutenanceDto.class);
            soutenanceDto.add(soutenanceDto1);
        }
        return soutenanceDto;
    }
}
