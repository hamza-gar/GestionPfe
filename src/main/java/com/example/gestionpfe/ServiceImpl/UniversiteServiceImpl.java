package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Dto.UniversiteDto;
import com.example.gestionpfe.Entities.Etablissement;
import com.example.gestionpfe.Entities.Universite;
import com.example.gestionpfe.Repositories.UniversiteRepository;
import com.example.gestionpfe.Services.UniversiteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversiteServiceImpl implements UniversiteService {
    ModelMapper modelMapper = new ModelMapper();
    Logger logger = org.slf4j.LoggerFactory.getLogger(UniversiteServiceImpl.class);

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    EtablissementServiceImpl etablissementService;

    @Override
    public UniversiteDto findUniversiteById(String idUniversite) {

        return null;
    }

    @Override
    public UniversiteDto findUniversiteByNom(String nomUniversite) {
        return null;
    }

    @Override
    public List<UniversiteDto> getAllUniversites(int page, int limit) {
        return null;
    }

    @Override
    public List<UniversiteDto> getAllUniversites() {
        List<UniversiteDto> universiteDtos = new ArrayList<>();
        List<Universite> universites = (List<Universite>) universiteRepository.findAll();
        for (Universite universite: universites){
            UniversiteDto universiteDto = modelMapper.map(universite, UniversiteDto.class);
            universiteDtos.add(universiteDto);
            logger.info("Universite added: " + universiteDto.getNomUniversite());
        }
        return universiteDtos;
    }

    @Override
    public List<EtablissementDto> getAllEtablissementsOfUniversite(String nomUniversite) {
        List<Etablissement> etablissements = universiteRepository.findByNomUniversite(nomUniversite).getEtablissements();
        List<EtablissementDto> etablissementDtos = new ArrayList<>();
        for (Etablissement etablissement: etablissements){
            EtablissementDto etablissementDto = modelMapper.map(etablissement, EtablissementDto.class);
            etablissementDtos.add(etablissementDto);
            logger.info("Etablissement added: " + etablissementDto.getNomEtablissement());
        }
        logger.info("Etablissements of universite: " + nomUniversite);
        return etablissementDtos;

    }
}
