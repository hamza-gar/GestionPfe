package com.example.gestionpfe.Services;


import com.example.gestionpfe.Dto.EquipeDto;

import java.util.List;

public interface EquipeService {
    EquipeDto addEquipe(String sujetId,EquipeDto equipeDto);
    EquipeDto getEquipeByIdEquipe(String idEquipe);
    EquipeDto getEquipeById(String id);
    EquipeDto updateEquipe(EquipeDto equipeDto);
    void deleteEquipe(String id);
    List<EquipeDto> getAllEquipes(int page, int limit);
}