package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Dto.UniversiteDto;

import java.util.List;

public interface UniversiteService {
    UniversiteDto findUniversiteById(String idUniversite);
    UniversiteDto findUniversiteByNom(String nomUniversite);
    List<UniversiteDto> getAllUniversites(int page, int limit);
    List<UniversiteDto> getAllUniversites();

    List<EtablissementDto> getAllEtablissementsOfUniversite(String nomUniversite);
}
