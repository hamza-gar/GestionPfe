package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.FiliereDto;


import java.util.List;

public interface FiliereService {
    FiliereDto addFiliere(FiliereDto filiereDTO);
    FiliereDto getFiliere(String nomFiliere);
    FiliereDto getFiliereByIdFiliere(String id);
    FiliereDto updateFiliere(String id,FiliereDto filiereDTO);
    void deleteFiliere(String id);
    List<FiliereDto> getAllFilieres(int page, int limit);
}
