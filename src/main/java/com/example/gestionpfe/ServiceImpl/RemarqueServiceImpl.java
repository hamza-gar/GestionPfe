package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.RemarqueDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Remarque;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
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
    EtudiantRepository etudiantRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    Utils util;

    @Override
    public RemarqueDto addRemarque(String username, RemarqueDto remarqueDto) {
        Remarque checkRemarque = remarqueRepository.findByIdRemarque(remarqueDto.getIdRemarque());
        if (checkRemarque != null) {
            logger.warn("remarque deja exist !!!");
            throw new RuntimeException("remarque deja exist !!!");
        }
        Enseignant enseignant = enseignantRepository.findByEmail(username);
        if (enseignant == null) {
            logger.warn("enseignant not found !!!");
            throw new RuntimeException("enseignant not found !!!");
        }
        Etudiant etudiant = etudiantRepository.findByIdEtudiant(remarqueDto.getEtudiant().getIdEtudiant());
        if (etudiant == null) {
            logger.warn("etudiant not found !!!");
            throw new RuntimeException("etudiant not found !!!");
        }
        if (!etudiant.getEquipe().get(0).getSujet().getSoutenance().getJurys().stream().anyMatch(jury -> jury.getEnseignant().getIdEnseignant().equals(enseignant.getIdEnseignant()))) {
            logger.warn("vous n'etes pas membre du jury de ce sujet");
            throw new RuntimeException("vous n'etes pas membre du jury de ce sujet");
        }
        Remarque remarqueEntity = new Remarque();
        remarqueEntity.setIdRemarque(util.generateUserId(32));
        remarqueEntity.setRemarque(remarqueDto.getRemarque());
        remarqueEntity.setNote(remarqueDto.getNote());
        remarqueEntity.setTarget(remarqueDto.getTarget());
        remarqueEntity.setEtudiant(etudiant);

        Remarque newRemarque = remarqueRepository.save(remarqueEntity);

        logger.info("remarque found successfully");

        return modelMapper.map(newRemarque, RemarqueDto.class);
    }

    @Override
    public List<RemarqueDto> getMyRemarques(String username) {
        Etudiant etudiant = etudiantRepository.findByEmail(username);
        if (etudiant == null) {
            logger.warn("etudiant not found !!!");
            throw new RuntimeException("etudiant not found !!!");
        }
        Iterable<Remarque> remarques = remarqueRepository.findAll();
        List<RemarqueDto> remarqueDtos = new ArrayList<>();
        if (remarques == null) {
            logger.warn("Il n'y a pas de remarques !!!");
            return remarqueDtos;
        }
        for (Remarque remarque : remarques) {
            if (remarque.getEtudiant().getIdEtudiant().equals(etudiant.getIdEtudiant())) {
                remarqueDtos.add(modelMapper.map(remarque, RemarqueDto.class));
            }
        }
        logger.info("remarques found successfully");
        return remarqueDtos;
    }

    @Override
    public RemarqueDto getRemarqueByIdRemarque(String id) {
        Remarque remarqueEntity = remarqueRepository.findByIdRemarque(id);
        if (remarqueEntity == null) throw new RuntimeException(id);

        RemarqueDto remarqueDto = new RemarqueDto();
        remarqueDto = modelMapper.map(remarqueEntity, RemarqueDto.class);
        logger.info("remarque found successfully");

        return remarqueDto;
    }

    @Override
    public RemarqueDto updateRemarque(String id, RemarqueDto remarque) {
        Remarque remarqueEntity = remarqueRepository.findByIdRemarque(id);
        if (remarqueEntity == null) throw new RuntimeException(id);

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
        if (remarqueEntity == null) throw new RuntimeException(id);
        remarqueRepository.delete(remarqueEntity);
        logger.info("remarque deleted successfully");
    }

    @Override
    public List<RemarqueDto> getAllRemarques(int page, int limit) {
        List<RemarqueDto> remarqueDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        List<Remarque> remarqueEntity = remarqueRepository.findAll(pageableRequest).getContent();
        for (Remarque remarque : remarqueEntity) {
            remarqueDto.add(modelMapper.map(remarque, RemarqueDto.class));
        }

        return remarqueDto;
    }

}
