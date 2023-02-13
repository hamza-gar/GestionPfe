package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.SujetDto;

import java.util.List;

public interface SujetService {
    SujetDto addSujet(SujetDto sujetDTO, String username);
    SujetDto getSujet(String nomSujet);
    SujetDto getSujetByIdSujet(String filiere);
    SujetDto getSujetById(String id);
    SujetDto updateSujet(String idEnseignant,String id,SujetDto sujetDTO);
    SujetDto lockSujet(String idEnseignant,String idSujet,String idEquipe);
    SujetDto validateSujet(String idEnseignant,SujetDto sujetDto,Boolean done);
    void deleteSujet(String id);
    List<SujetDto> getAllSujets(String username,int page, int limit);
    List<SujetDto> getAllSujetsByFiliere(String filiere, int page, int limit);
    List<SujetDto> getAllMyLockedSujets(String username,int page, int limit);
    long countSujets(String username);
    long countMySujets(String username);
    List<SujetDto> getAllMySujets(String username,int page, int limit);
    List<SujetDto> getAllSujetsFiltered(int page, int limit,String universite,String etablissement,String departement);

    Boolean isValidated(String mail, String idSujet);
}
