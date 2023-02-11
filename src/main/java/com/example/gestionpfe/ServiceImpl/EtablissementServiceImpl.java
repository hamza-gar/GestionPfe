package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Entities.Etablissement;
import com.example.gestionpfe.Repositories.EtablissementRepository;
import com.example.gestionpfe.Services.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtablissementServiceImpl implements EtablissementService {

    @Autowired
    EtablissementRepository etablissementRepository;

    @Override
    public EtablissementDto addEtablissement(EtablissementDto etablissementDto) {
        return null;
    }

    @Override
    public List<EtablissementDto> getAllEtablissements() {
        List<Etablissement> etablissements = etablissementRepository.findAll();
        List<EtablissementDto> etablissementDtos = new ArrayList<>();
        for (Etablissement etablissement : etablissements) {
            EtablissementDto etablissementDto = new EtablissementDto();
            etablissementDto.setIdEtablissement(etablissement.getIdEtablissement());
            etablissementDto.setNomEtablissement(etablissement.getNomEtablissement());
            etablissementDtos.add(etablissementDto);
        }
        return etablissementDtos;
    }
}
