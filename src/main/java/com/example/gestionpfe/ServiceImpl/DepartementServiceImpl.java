package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DepartementDto;
import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DepartementRepository;
import com.example.gestionpfe.Services.DepartementService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartementServiceImpl implements DepartementService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DepartementRepository departementRepository;

    @Override
    public DepartementDto addDepartement(DepartementDto departementDTO) {
        Departement checkDepartement = departementRepository.findByNomDepartement(departementDTO.getNomDepartement());
        if (checkDepartement != null) throw new RuntimeException("departement deja exist !!!");
        Departement departementEntity = new Departement();
        departementEntity = modelMapper.map(departementDTO, Departement.class);
        logger.info("departementEntity : " + departementEntity);
        Departement newDepartement = departementRepository.save(departementEntity);

        DepartementDto newDepartementDto = new DepartementDto();
        newDepartementDto = modelMapper.map(newDepartement, DepartementDto.class);

        logger.info("departement found successfully");
        return newDepartementDto;

    }

    @Override
    public DepartementDto getDepartement(String nomDepartement) {
        Departement departementEntity = departementRepository.findByNomDepartement(nomDepartement);
        if (departementEntity == null) throw new RuntimeException(nomDepartement);

        DepartementDto departementDto = new DepartementDto();
        departementDto = modelMapper.map(departementEntity, DepartementDto.class);
        logger.info("departement found successfully");
        return departementDto;

    }

    @Override
    public DepartementDto getDepartementById(String id) {
        Departement departementEntity = departementRepository.findById(id);
        if (departementEntity == null) throw new RuntimeException(id);

        DepartementDto departementDto = new DepartementDto();
        departementDto = modelMapper.map(departementEntity, DepartementDto.class);
        logger.info("departement found successfully");
        return departementDto;
    }

    @Override
    public DepartementDto updateDepartement(String id, DepartementDto departementDTO) {
        Departement departementEntity = departementRepository.findById(id);
        if (departementEntity == null) throw new RuntimeException(id);
        if(departementDTO.getNomDepartement() != null) departementEntity.setNomDepartement(departementDTO.getNomDepartement());
        if(departementDTO.getEnseignants() != null) departementEntity.setEnseignants(departementDTO.getEnseignants());
        if(departementDTO.getFilieres() != null) departementEntity.setFilieres(departementDTO.getFilieres());

        Departement updatedDepartement = departementRepository.save(departementEntity);
        DepartementDto updatedDepartementDto = new DepartementDto();
        updatedDepartementDto = modelMapper.map(updatedDepartement, DepartementDto.class);
        logger.info("departement updated successfully");

        return updatedDepartementDto;
    }

    @Override
    public void deleteDepartement(String id) {
        Departement departementEntity = departementRepository.findById(id);
        if (departementEntity == null) throw new RuntimeException(id);
        departementRepository.delete(departementEntity);
        logger.info("departement deleted successfully");
    }

    @Override
    public List<DepartementDto> getAllDepartements(int page, int limit) {
        List<Departement> departements = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        departements = departementRepository.findAll(pageableRequest).getContent();

        List<DepartementDto> departementDtos = new ArrayList<>();
        for (Departement departement : departements) {
            DepartementDto departementDto = new DepartementDto();
            departementDto = modelMapper.map(departement, DepartementDto.class);

            departementDtos.add(departementDto);
        }
        logger.info("departements found successfully");
        return departementDtos;
    }
}
