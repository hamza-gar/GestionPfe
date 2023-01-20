package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.SujetDto;

import java.util.List;

public interface SujetService {
    SujetDto addSujet(SujetDto sujetDTO);
    SujetDto getSujet(String nomSujet);
    SujetDto getSujetById(String id);
    SujetDto updateSujet(String id,SujetDto sujetDTO);
    void deleteSujet(String id);
    List<SujetDto> getAllSujets(int page, int limit);
}
