package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.EquipeDto;

import java.util.List;

public interface EquipeService {
    EquipeDto addEquipe(EquipeDto equipeDto);
    EquipeDto getEquie(String nomDomaine);
    EquipeDto getEquipeById(String id);
    EquipeDto updateEquipe(String id,EquipeDto equipeDto);
    void deleteEquipe(String id);
    List<EquipeDto> getAllEquipes(int page, int limit);
}
