package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.EtablissementDto;

import java.util.List;

public interface EtablissementService {
    EtablissementDto addEtablissement(EtablissementDto etablissementDto);
    List<EtablissementDto> getAllEtablissements();
}
